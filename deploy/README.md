# 尚庭公寓 Docker 部署文档

> 适用环境：CentOS 7 / RHEL 7（内核 3.10，**strict SELinux**）+ Docker 20.10+ + Docker Compose v2
> 最后更新：2026-11-25

---

## 一、部署架构

```
┌──────────────────────────────────────────────────────────────┐
│  宿主机（虚拟机）                                              │
│                                                              │
│  ┌── 已有基础设施（外部 Docker）──┐  ┌── docker compose 编排 ──┐
│  │                                │  │                        │
│  │  mysql8   :3306                │  │  gateway           :8088│
│  │  nacos    :8848/9848/9849      │  │  service-user      :8082│
│  │  redis    :6379                │  │  service-apartment :8083│
│  │                                │  │  service-lease     :8084│
│  │  全部在 nacos-net 网络上        │  │  web-admin (Nginx):80  │
│  │                                │  │  web-h5   (Nginx):81  │
│  └────────────────────────────────┘  └────────────────────────┘
│                           ↑ 全部加入 nacos-net ↑              │
└──────────────────────────────────────────────────────────────┘
```

**核心**：MySQL / Nacos / Redis 三个基础容器已经在宿主机运行并加入了 `nacos-net` 网络，compose 编排的后端和前端容器通过 `external: true` 共享这个网络，**不需要在 compose 里重复定义基础设施**。

---

## 二、前提条件

### 1. Docker 开机自启

```bash
systemctl enable docker
systemctl start docker
```

### 2. 基础设施已就绪

```bash
# 确认 nacos-net 网络存在
docker network ls | grep nacos-net

# 确认三个基础容器在运行且在网络上
docker ps --format "table {{.Names}}\t{{.Status}}" | grep -E "mysql8|nacos|redis"
docker network inspect nacos-net --format '{{range .Containers}}{{.Name}} {{end}}'
```

必须能看到 `mysql8`、`nacos`、`redis` 三个容器。

### 3. 宿主机端口未占用

```bash
# 需要这些端口：80 (管理端前端) / 81 (用户端前端) / 8082-8084 (后端服务) / 8088 (Gateway)
ss -tlnp | grep -E ":80 |:81 |:8082|:8083|:8084|:8088"
```

如果 80 被占用（比如已有 Nginx），改 `docker-compose.yml` 里 web-admin 的 `ports: "80:80"` 为 `"8080:80"`，浏览器访问 `http://<IP>:8080/` 即可。

---

## 三、目录结构

deploy 目录完整内容：

```
deploy/
├── docker-compose.yml              # 6 个服务编排 + 3 个健康检查辅助容器
├── Dockerfile                      # 后端 Java 17 基础镜像
├── .env.dev                        # 开发环境变量（容器名连接，mysql8/nacos/redis）
├── .env.local                      # 本地开发变量（IP 连接 192.168.100.101）
├── README.md                       # 本文档
├── jars/                           # 后端 Spring Boot fat jar
│   ├── gateway-1.0-SNAPSHOT.jar
│   ├── service-user-1.0-SNAPSHOT.jar
│   ├── service-apartment-1.0-SNAPSHOT.jar
│   └── service-lease-1.0-SNAPSHOT.jar
└── web/
    ├── admin/                      # rentHouseAdmin 管理端（Vite 构建产物）
    │   ├── Dockerfile.nginx        # Nginx 镜像构建文件
    │   ├── nginx.conf              # 反代 /admin → gateway:8088
    │   └── dist/                   # Vite build 产物
    └── h5/                         # rentHouseH5 用户端
        ├── Dockerfile.nginx
        ├── nginx.conf              # 反代 /app → gateway:8088
        └── dist/
```

---

## 四、首次部署

```bash
# 1. 上传 deploy 目录到虚拟机（示例路径：/root/deploy）
scp -r deploy/ root@<虚拟机IP>:/root/

# 2. 登录虚拟机
ssh root@<虚拟机IP>

# 3. 进入目录
cd /root/deploy

# 4. 构建并启动（首次需要 --build 构建所有镜像）
docker compose up -d --build

# 5. 查看所有容器状态
docker ps -a

# 6. 查看实时日志（可选，确认服务注册到 Nacos）
docker compose logs -f --tail 50
```

### 成功标志

`docker ps` 里看到以下 6 个核心容器 **Up**：

| 容器 | 状态 |
|---|---|
| gateway | Up |
| service-user | Up |
| service-apartment | Up |
| service-lease | Up |
| web-admin | Up |
| web-h5 | Up |

（nacos-check / mysql-check / redis-check 是一次性健康检查容器，`Exited (0)` 属正常）

---

## 五、浏览器访问

| 服务 | URL | 说明 |
|---|---|---|
| **管理端前端** | `http://<虚拟机IP>/` | rentHouseAdmin，Nginx 反代 `/admin/**` 到 Gateway |
| **用户端前端** | `http://<虚拟机IP>:81/` | rentHouseH5，Nginx 反代 `/app/**` 到 Gateway |
| **Nacos 控制台** | `http://<虚拟机IP>:8848/nacos` | 账号 `nacos` / `nacos`，查看服务注册情况 |
| **Gateway 直连探活** | `http://<虚拟机IP>:8088/admin/login/captcha` | 返回 JSON = Gateway 正常 |

### 接口探活（PowerShell / curl）

```bash
# curl 验证 Gateway → service-user 全链路
curl http://127.0.0.1:8088/admin/login/captcha
# 应返回类似：{"code":200,"data":{"captchaId":"xxx","image":"data:image/png;base64,..."}}
```

---

## 六、常用运维命令

```bash
# ========== 查看状态 ==========
docker ps                           # 运行中的容器
docker ps -a                        # 全部（含已退出）
docker compose ps                   # compose 视角

# ========== 日志 ==========
docker logs -f web-admin --tail 100           # 持续跟随
docker compose logs gateway service-user      # 同时看多个服务
docker compose logs -f --tail 50              # 全部服务实时日志

# ========== 重启 ==========
docker compose restart gateway                # 重启单个
docker compose restart                        # 重启全部

# ========== 停止 / 启动 ==========
docker compose stop                           # 停止全部（但保留容器）
docker compose stop web-admin web-h5          # 停止指定
docker compose start                          # 启动

# ========== 重建（改了 Dockerfile 或 jar 后）==========
docker compose build --no-cache web-admin web-h5    # 前端
docker compose build --no-cache gateway             # 后端
docker compose up -d --build                        # 重新构建并启动

# ========== 彻底清理 ==========
docker compose down                           # 停并删容器（镜像保留）
docker compose down -v                        # 连匿名 volume 一起删
docker images prune                           # 删悬空镜像
docker system prune -a                        # ⚠️ 清空所有未使用的镜像

# ========== 清理旧的健康检查容器 ==========
docker rm nacos-check mysql-check redis-check

# ========== 进入容器调试 ==========
docker exec -it web-admin sh                   # sh（alpine 没有 bash）
docker exec -it gateway sh
docker network inspect nacos-net               # 查看网络上有哪些容器
```

---

## 七、配置文件说明

### .env.dev（开发环境，compose 默认读这个）

容器网络内部连接：

```
NACOS_HOST=nacos          # 容器名
NACOS_PORT=8848
MYSQL_HOST=mysql8
MYSQL_PORT=3306
MYSQL_DATABASE=lease
MYSQL_USERNAME=root
MYSQL_PASSWORD=123456
REDIS_HOST=redis
REDIS_PORT=6379
# MINIO_* 可选，service-apartment 的 MinIO 如在其他网络需填
```

### .env.local（本地开发用）

本地 IDEA 直连 192.168.100.101：

```bash
docker compose --env-file .env.local up -d
```

### docker-compose.yml 关键设计

| 设计点 | 说明 |
|---|---|
| `networks.nacos-net.external: true` | 加入已有网络，不重复创建 |
| `depends_on: nacos-check + mysql-check + redis-check` | 确保基础设施就绪后再启动 |
| `restart: unless-stopped` | 宿主机重启后自动恢复 |
| `privileged: true`（前端容器） | 绕过 CentOS 7 strict SELinux |
| `security_opt: label:disable` | 配合 privileged 彻底放开限制 |
| `resolver 127.0.0.11`（nginx.conf） | 延迟 DNS 解析，避免 gateway 未就绪时启动失败 |
| `pid /tmp/nginx.pid`（-g 参数） | 绕过 SELinux 对 /run 的写入限制 |

---

## 八、常见问题

### Q1: nginx 容器反复重启，`pwrite() "/run/nginx.pid" failed (1: Operation not permitted)`

CentOS 7 strict SELinux 拦截容器写 /run 目录。**已通过三重防线解决**：
1. Dockerfile 里 `ENTRYPOINT []` 干掉官方 entrypoint
2. `-g "pid /tmp/nginx.pid"` 改 pid 路径
3. `privileged: true` 彻底绕过 SELinux

### Q2: nginx 容器 `host not found in upstream "gateway"`

nginx 启动时 gateway 还没在网络上就绪。**已通过 `resolver 127.0.0.11` + `set + 变量 proxy_pass` 解决**——让 nginx 延迟 DNS 解析，不再要求启动时 gateway 必须存在。

### Q3: docker build 时 heredoc 写 nginx.conf 被 Windows→Linux 传输破坏

CRLF 换行 + Dockerfile heredoc 组合导致内容错乱。**改用简单 `sed -i` 命令代替 heredoc**。

### Q4: Gateway 显示 unhealthy

项目没加 Spring Boot Actuator，healthcheck 路径永远 404。**不影响功能**，可以忽略。如果要消掉这个状态，删掉 docker-compose.yml 里 gateway 的 healthcheck 块即可。

### Q5: nacos-check / mysql-check / redis-check 一直 Up

compose 里写了 `restart: unless-stopped`，但它们是一次性脚本跑完就 exit 0。**不影响功能**，可以手动 `docker rm nacos-check mysql-check redis-check` 清理，或者在 compose 里给它们加 `restart: "no"`。

### Q6: 前端浏览器打开是空白页/404

检查 Nginx 反代：
1. 容器内静态文件是否存在：`docker exec web-admin ls /usr/share/nginx/html`
2. Nginx 配置是否正确：`docker exec web-admin cat /etc/nginx/conf.d/default.conf`
3. 浏览器 F12 Network 看 API 请求发到哪了（应该是 `/admin/**` → 8088）

### Q7: 改了 .env.dev 不生效

compose 只在第一次创建容器时读 .env，改了之后要：
```bash
docker compose down
docker compose up -d
```

或者强制重建：
```bash
docker compose up -d --force-recreate
```

### Q8: 宿主机重启后容器没自动起来

```bash
# 1. 确认 Docker 自启
systemctl enable docker
systemctl is-enabled docker

# 2. 确认容器 restart 策略
docker inspect web-admin --format '{{.HostConfig.RestartPolicy.Name}}'
# 应输出 unless-stopped

# 3. 如果有容器之前被手动 docker stop 过，手动再 up 一次
docker compose up -d
# 之后宿主机重启就会自动恢复了
```

---

## 九、环境变量注入机制

所有硬编码 IP 的配置都已改成 `${VAR:默认值}` 语法：

| 环境变量 | 默认值 | 使用场景 |
|---|---|---|
| `NACOS_HOST` | `192.168.100.101` | Nacos 地址 |
| `NACOS_PORT` | `8848` | Nacos 端口 |
| `MYSQL_HOST` | `192.168.100.101` | MySQL 地址 |
| `MYSQL_PORT` | `3306` | MySQL 端口 |
| `MYSQL_DATABASE` | `lease` | 数据库名 |
| `MYSQL_USERNAME` | `root` | 用户名 |
| `MYSQL_PASSWORD` | `123456` | 密码 |
| `REDIS_HOST` | `192.168.100.101` | Redis 地址 |
| `REDIS_PORT` | `6379` | Redis 端口 |
| `MINIO_ENDPOINT` | `http://192.168.100.101:9000` | MinIO 地址 |

Docker Compose 通过 `env_file: - .env.dev` 注入。IDE 本地启动时走默认值（192.168.100.101），零配置。

---

## 十、技术栈版本

| 组件 | 版本 |
|---|---|
| Spring Boot | 3.0.5 |
| Spring Cloud | 2022 |
| Spring Cloud Alibaba | Nacos Discovery |
| JDK | 17 |
| MySQL | 8.0 |
| Nacos | v2.5.1 |
| Redis | 7 |
| Nginx (前端) | alpine 1.31 |
| Docker | 20.10+ |
| Docker Compose | v2 |
