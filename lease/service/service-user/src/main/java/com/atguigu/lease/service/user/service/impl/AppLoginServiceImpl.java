package com.atguigu.lease.service.user.service.impl;

import com.atguigu.lease.common.constant.RedisConstant;
import com.atguigu.lease.common.exception.LeaseException;
import com.atguigu.lease.common.result.ResultCodeEnum;
import com.atguigu.lease.common.utils.CodeUtil;
import com.atguigu.lease.common.utils.JwtUtil;
import com.atguigu.lease.model.entity.UserInfo;
import com.atguigu.lease.model.enums.BaseStatus;
import com.atguigu.lease.service.user.service.AppLoginService;
import com.atguigu.lease.service.user.service.SmsService;
import com.atguigu.lease.service.user.service.UserInfoService;
import com.atguigu.lease.service.user.vo.app.AppLoginVo;
import com.atguigu.lease.service.user.vo.app.AppUserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class AppLoginServiceImpl implements AppLoginService {

    @Autowired
    private SmsService smsService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private UserInfoService userInfoService;

    /** 开发环境固定验证码：永久有效，无需先点获取验证码，登录时直接输入即可 */
    private static final String FIXED_CODE = "123456";

    @Override
    public void getCode(String phone) {
        // 固定验证码，控制台 Mock 打印；不再受 60s 重发限制与 10 分钟过期限制
        smsService.sendCode(phone, FIXED_CODE);
    }

    @Override
    public String login(AppLoginVo loginVo) {
        if (loginVo.getPhone() == null) {
            throw new LeaseException(ResultCodeEnum.APP_LOGIN_PHONE_EMPTY);
        }
        if (loginVo.getCode() == null) {
            throw new LeaseException(ResultCodeEnum.APP_LOGIN_CODE_EMPTY);
        }

        // 固定验证码 123456 直接通过，跳过 Redis 校验（开发环境便利）
        if (!FIXED_CODE.equals(loginVo.getCode())) {
            String key = RedisConstant.APP_LOGIN_PREFIX + loginVo.getPhone();
            String code = stringRedisTemplate.opsForValue().get(key);
            if (code == null) {
                throw new LeaseException(ResultCodeEnum.APP_LOGIN_CODE_EXPIRED);
            }
            if (!code.equals(loginVo.getCode())) {
                throw new LeaseException(ResultCodeEnum.APP_LOGIN_CODE_ERROR);
            }
        }

        UserInfo userInfo = userInfoService.getByPhone(loginVo.getPhone());
        if (userInfo == null) {
            userInfo = new UserInfo();
            userInfo.setPhone(loginVo.getPhone());
            userInfo.setStatus(BaseStatus.ENABLE);
            userInfo.setNickname("用户-" + loginVo.getPhone().substring(7));
            userInfoService.register(userInfo);
        } else {
            if (userInfo.getStatus() == BaseStatus.DISABLE) {
                throw new LeaseException(ResultCodeEnum.APP_ACCOUNT_DISABLED_ERROR);
            }
        }
        return JwtUtil.createToken(userInfo.getId(), userInfo.getPhone());
    }

    @Override
    public AppUserInfoVo getLoginUserInfoById(Long userId) {
        UserInfo info = userInfoService.getById(userId);
        return new AppUserInfoVo(info.getNickname(), info.getAvatarUrl());
    }
}
