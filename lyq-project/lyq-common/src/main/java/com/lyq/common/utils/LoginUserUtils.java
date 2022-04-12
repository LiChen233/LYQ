package com.lyq.common.utils;

import com.lyq.system.entity.sys_user.vo.LoginUserInfo;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @email 2434017367@qq.com
 * @author: zhy
 * @date: 2020/3/19
 * @time: 10:39
 */
public class LoginUserUtils {

    private static final String KEY = "loginUserInfo_";

    private static HttpServletRequest getRequest(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }

    /**
     * 设置登陆用户信息
     * @param loginUserInfo
     */
    public static void setLoginUserInfo(LoginUserInfo loginUserInfo){
        if (loginUserInfo != null){
            HttpServletRequest request = getRequest();
            request.setAttribute(KEY, loginUserInfo);
        }
    }

    /**
     * 获取登录用户信息
     */
    public static LoginUserInfo getLoginUserInfo(){
        HttpServletRequest request = getRequest();
        LoginUserInfo loginUserInfo = (LoginUserInfo) request.getAttribute(KEY);
        return loginUserInfo;
    }

    /**
     * 获取登录人员id
     * @return
     */
    public static String getLoginUserId(){
        LoginUserInfo loginUserInfo = getLoginUserInfo();
        return loginUserInfo.getId();
    }

    public static boolean isAdmin(){
        String loginUserId = getLoginUserId();
        return "1".equals(loginUserId);
    }

}
