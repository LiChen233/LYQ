package com.lyq.admin.system.service.sys_user.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lyq.admin.system.entity.sys_user.LoginFo;
import com.lyq.admin.system.entity.sys_user.LoginVo;
import com.lyq.admin.system.service.sys_user.AdminSysUserService;
import com.lyq.common.exception.RRException;
import com.lyq.common.result.Result;
import com.lyq.common.utils.RedisUtils;
import com.lyq.system.entity.sys_user.SysUser;
import com.lyq.system.service.sys_user.SysUserService;
import com.lyq.common.utils.IPUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service("adminSysUserService")
public class AdminSysUserServiceImpl implements AdminSysUserService {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 获取登录验证图片
     * @return
     */
    @Override
    public void getLoginVerifyImg(HttpServletResponse response){
        //定义图形验证码的长、宽、验证码字符数、干扰元素个数
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(180, 60, 4, 100);
        //图形验证码写出，可以写出到文件，也可以写出到流
        try {
            lineCaptcha.write(response.getOutputStream());
            // 添加到redis缓存中
            String code = lineCaptcha.getCode();
            String key = getLoginVerifyRedisCodeKey();
            redisUtils.set(key, code, DateUnit.MINUTE.getMillis() * 2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getLoginVerifyRedisCodeKey(){
        String ip = IPUtil.getIpAddr();
        String key = "loginVerifyCode" + ip;
        return key;
    }

    @Override
    public Result login(LoginFo loginFo) {
        String account = loginFo.getAccount();
        String verifyCode = loginFo.getVerifyCode();

        // 判断验证码是否正确
        String key = getLoginVerifyRedisCodeKey();
        String code = redisUtils.get(key, 0);
        if (StrUtil.isNotEmpty(verifyCode)) {
            if (!verifyCode.equalsIgnoreCase(code)){
                return Result.error("验证码错误");
            }
        } else {
            return Result.error("验证码不能为空");
        }

        // 记录账号密码错误次数一天不能多于10次
        long loginNumOutTime = DateUnit.HOUR.getMillis() * 24;
        String loginNumRedisKey = "loginNum:" + account;
        String loginNumStr = redisUtils.get(loginNumRedisKey, loginNumOutTime);
        if (StrUtil.isNotEmpty(loginNumStr)){
            int loginNum = Integer.parseInt(loginNumStr);
            if (loginNum > 10){
                throw new RRException("密码错误超过10次账号已被冻结24小时，联系管理员进行解冻");
            }
        }

        String password = loginFo.getPassword();
        password = sysUserService.passEncr(password);
        SysUser sysUser = sysUserService.getOne(new LambdaQueryWrapper<SysUser>()
                .eq(SysUser::getAccount, account)
                .eq(SysUser::getPassword, password));
        if (sysUser != null){
            // 封装登录成功后的用户信息
            StpUtil.login(sysUser.getId());
            LoginVo loginVo = new LoginVo(sysUser);
            loginVo.setToken(StpUtil.getTokenValue());
            return Result.ok(loginVo);
        }else{
            int loginNum = 0;
            if (StrUtil.isNotEmpty(loginNumStr)){
                loginNum = Integer.parseInt(loginNumStr);
            }
            loginNum += 1;
            redisUtils.set(loginNumRedisKey, loginNum, loginNumOutTime);

            return Result.error("账号或密码错误");
        }

    }

}
