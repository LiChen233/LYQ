package com.lyq.admin.system.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyq.admin.system.entity.sys_user.LoginFo;
import com.lyq.common.utils.LoginUserUtils;
import com.lyq.system.entity.sys_user.fo.SysUserQueryFo;
import com.lyq.admin.system.service.sys_user.AdminSysUserService;
import com.lyq.common.result.Result;
import com.lyq.system.entity.sys_user.fo.SysUserFo;
import com.lyq.system.entity.sys_user.vo.SysUserVo;
import com.lyq.system.service.sys_user.SysUserService;
import com.lyq.common.annotation.ReqApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/admin_sys_user")
public class AdminSysUserController {

    @Autowired
    private AdminSysUserService adminSysUserService;

    @Autowired
    private SysUserService sysUserService;

    /**
     * 获取登录验证图片
     * @return
     */
    @GetMapping("/getLoginVerifyImg")
    public void getLoginVerifyImg(HttpServletResponse response){
        adminSysUserService.getLoginVerifyImg(response);
    }

    /**
     * 登录
     * @param loginFo
     * @return
     */
    @PostMapping("/login")
    public Result login(LoginFo loginFo){
        Result login = adminSysUserService.login(loginFo);
        return login;
    }

    /**
     * 退出登录
     * @return
     */
    @GetMapping("/logout")
    public Result logout(){
        StpUtil.logout();
        return Result.ok();
    }

    /**
     * 获取登录用户信息
     * @return
     */
    @GetMapping("/getUserInfo")
    public Result getUserInfo(){
        String loginUserId = LoginUserUtils.getLoginUserId();
        SysUserVo userInfo = sysUserService.getUserInfo(loginUserId);
        return Result.ok(userInfo);
    }

    /**
     * 用户新增
     * @param sysUserFo
     * @return
     */
    @ReqApi(value = "用户新增", permission = "sys:user:add")
    @PostMapping("/add")
    public Result add(@RequestBody SysUserFo sysUserFo){
        return sysUserService.add(sysUserFo);
    }

    /**
     * 用户编辑
     * @param sysUserFo
     * @return
     */
    @ReqApi(value = "用户编辑", permission = "sys:user:edit")
    @PutMapping ("/edit")
    public Result edit(@RequestBody SysUserFo sysUserFo){
        return sysUserService.edit(sysUserFo);
    }

    /**
     * 用户删除
     * @param userId
     * @return
     */
    @ReqApi(value = "用户删除", permission = "sys:user:del")
    @DeleteMapping ("/del")
    public Result del(@RequestParam String userId){
        return sysUserService.del(userId);
    }

    /**
     * 用户管理分页查询
     * @param sysUserPageQueryFo
     * @return
     */
//    @ReqApi(value = "用户管理分页查询", permission = "sys:user:queryPage")
    @PostMapping("/queryPage")
    public Result queryPage(@RequestBody SysUserQueryFo sysUserPageQueryFo){
        Page<SysUserVo> page = sysUserService.queryPage(sysUserPageQueryFo);
        return Result.ok(page);
    }

}
