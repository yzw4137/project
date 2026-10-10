# 尚庭公寓（lease）微服务拆分迁移计划

## Context（背景与目标）

当前 `lease` 项目虽然搭好了微服务骨架（Nacos 注册、Gateway 网关、OpenFeign、三个 service 模块），但**所有业务代码仍集中在 web-admin 和 web-app 内部**，直连数据库；`service-apartment`、`service-lease` 只有空启动类，`service-user` 仅有一个 demo 接口。

目标：把业务按领域真正下沉到 service 微服务，web 层退化为 BFF（接口聚合 + Feign 调用），成为可扩展的标准微服务结构，方便后续新增模块。

**已确认决策**：
- 基础数据（图片、标签、设施、属性、费用、省市区、租期、支付方式）全部归入 **service-apartment**（房源域）。
- 采用**分阶段迁移**：先完整迁 service-apartment 跑通链路，验证模式后再迁 service-user、service-lease。

## 服务边界总览

| 微服务 | 端口 | 领域 | 包含的业务 |
|---|---|---|---|
| service-apartment | 8083 | 房源域 | 公寓、房间、图片、标签、配套、属性、费用、省市区、租期、支付方式、文件上传(MinIO) |
| service-user | 8082 | 用户域 | 用户(UserInfo)、浏览历史、预约看房、系统用户、岗位 |
| service-lease | 8084 | 租约域 | 租约(LeaseAgreement)、租约过期定时任务 |
| web-admin | 8080 | BFF | 后台接口聚合、登录认证(JWT)、拦截器 |
| web-app | 8081 | BFF | 用户端接口聚合、短信登录(JWT) |
| gateway | 8088 | 网关 | 路由、token 校验、透传 user 信息 |

跨域查询（租约要公寓信息、预约要公寓信息等）在 **web 层做 Feign 聚合**。

## 通用迁移模式（以 service-user 为样板，已验证）

每个微服务迁移遵循同一套步骤：

1. **pom**：service 子模块继承 `service` 父 pom 即自动获得 service-util/model/nacos/loadbalancer（含 MyBatis-Plus、MySQL、Redis、MinIO）。若该服务需调用别的服务，额外加 `api` + `spring-cloud-starter-openfeign`。
2. **启动类**：`@SpringBootApplication(scanBasePackages="com.atguigu.lease")` + `@EnableDiscoveryClient`；需定时任务加 `@EnableScheduling`；需 Feign 加 `@EnableFeignClients(basePackages="com.atguigu.lease.api")`。
3. **application.yml**：复用 service-user 配置，改 `server.port` 和 `spring.application.name`；mapper xml 配 `mybatis-plus.mapper-locations: classpath*:/mapper/**/*.xml`。
4. **代码迁移**：在 `com.atguigu.lease.service.<域>` 下建 `controller/`（路径去掉 `/admin`、`/app` 前缀）、`service/`+`impl/`、`mapper/`；XML 放 `resources/mapper/`。
5. **api 模块**：在 `com.atguigu.lease.api.<域>` 下定义 `@FeignClient` 接口，方法签名与 service 的 Controller 一致，返回 `Result<T>`。
6. **改造 web 层**：ServiceImpl 去掉 `extends ServiceImpl` 和直连 mapper，改为注入 Feign Client 调用；Controller 不动。

`@MapperScan("com.atguigu.lease.**.mapper")`（在 service-util 的 MybatisPlusConfiguration 中）会自动扫描各服务 mapper，无需额外配置。

## 第一阶段：迁移 service-apartment（重点，作为模板）

### 1. service-apartment 侧
- **Controller**（从 web-admin 迁移，去 `/admin` 前缀）：Apartment、Room、FileUpload、Region、Fee、Attr、Facility、Label、LeaseTerm、PaymentType。
- **Service/Impl + Mapper**：上述各业务 + 中间表（ApartmentLabel、RoomFacility 等），整体从 web-admin 迁入；web-app 独有的查询方法（如 `selectMinRentByApartmentId`）合并进来。
- **Mapper XML**：以 web-admin 版为准迁入 `resources/mapper/`，合并 web-app 独有查询。
- **VO 处理**：service 层返回的查询结果 VO（如 ApartmentDetailVo、ApartmentItemVo、RoomDetailVo）迁到 **model 模块的 vo 包**，供 api(Feign) 和 web 共用；web 层接收前端参数的 VO 保留在 web 层。
- 本域内多表关联（公寓详情的图片/标签/设施/费用联查）**全部保留在 service-apartment 内部**，无需跨服务。

### 2. api 模块
在 `api/.../apartment/` 下按子域建多个 Feign Client：ApartmentClient、RoomClient、LabelClient、FacilityClient、AttrClient、FeeClient、RegionClient、LeaseTermClient、PaymentTypeClient、FileClient。

### 3. 改造 web-admin / web-app
- 各 ServiceImpl 改为注入对应 Feign Client 调用，删除直连 mapper 代码与本域组装逻辑（已下沉到 service）。
- Controller 保持不变。
- web 层的中间表 Service（ApartmentLabel 等）逻辑已封装进 service 的 saveOrUpdate，web 层不再需要。

### 4. 验证（第一阶段）
启动：Nacos → MySQL/Redis/MinIO → service-apartment → web-admin → web-app → gateway。
通过网关 8088 验证：登录、公寓分页/详情/增改、省市区联动、标签/设施/属性/费用 CRUD、文件上传、app 端公寓详情。

## 第二阶段：迁移 service-user
- 迁入：UserInfo（扩展现有 demo）、BrowsingHistory、ViewAppointment、SystemUser、SystemPost 的 controller/service/mapper/xml。
- api 新增：扩展 UserInfoClient，新增 BrowsingHistoryClient、ViewAppointmentClient、SystemUserClient、SystemPostClient。
- **跨域聚合**：预约详情/浏览历史需要公寓、房间信息 → web 层注入 ApartmentClient/RoomClient 聚合。
- web-app 的短信登录、JWT 生成保留在 web-app（BFF 职责）。
- 验证：app 短信登录、浏览历史、预约、admin 用户/预约管理。

## 第三阶段：迁移 service-lease
- 迁入：LeaseAgreement 的 controller/service/mapper/xml；ScheduleTask 定时任务迁到 service-lease（启动类加 @EnableScheduling）。
- api 新增：LeaseAgreementClient。
- **跨域聚合**：租约详情/列表需要公寓、房间、支付方式、租期 → web 层注入各 Feign Client 聚合；列表分页只查租约表，详情再聚合（避免 N+1 复杂联表）。
- 验证：admin 租约管理（含跨域聚合）、定时任务更新过期租约。

## 横切关注点
- **认证**：gateway 已校验 token 并透传 user-id/username header；web 层保留 AuthenticationInterceptor + JWT 生成；service 层不解析 token，需要用户信息时由 web 层从 LoginUserHolder 取出后作为 Feign 参数显式传入（简单清晰）。
- **异常**：service-util 已有 GlobalExceptionHandler，service 抛 LeaseException 会被包成 Result 返回；web 层 Feign 调用后检查 Result code。
- **事务**：单域内多表操作（如保存公寓+图片+标签）在 service 内部，本地事务有效；暂不涉及分布式事务。

## 收尾
清理 web 层残留的 mapper xml 和不再使用的 Service；全链路回归。

## 风险提示
- web-admin 与 web-app 存在重复的 mapper xml，以 web-admin 为准合并，迁移后删除 web 层对应 xml。
- 改动体量大，严格按阶段验证；每阶段提交一次，便于回滚。
