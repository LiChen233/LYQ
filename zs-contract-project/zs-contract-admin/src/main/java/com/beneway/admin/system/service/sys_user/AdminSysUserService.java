package com.beneway.admin.system.service.sys_user;

import com.beneway.admin.system.entity.sys_user.LoginFo;
import com.beneway.common.common.result.Result;

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
