package com.lyq.admin.system.service.sys_user;

import com.lyq.admin.system.entity.sys_user.LoginFo;
import com.lyq.common.result.Result;

import javax.servlet.http.HttpServletResponse;

public interface AdminSysUserService {

    /**
     * 获取登录验证图片
     * @return
     */
    void getLoginVerifyImg(HttpServletResponse response);

    /**
     * 登录
     * @param loginFo
     * @return
     */
    Result login(LoginFo loginFo);

}
