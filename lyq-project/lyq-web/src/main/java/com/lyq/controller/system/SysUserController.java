package com.lyq.controller.system;

import com.lyq.common.result.Result;
import com.lyq.system.entity.sys_user.vo.SysUserComVo;
import com.lyq.system.service.sys_user.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create by zhy on 2022/3/5 10:37
 */
@RestController()
@RequestMapping("/sys_user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 获取前端统一组件的用户信息
     * @return
     */
    @GetMapping("/getComUserInfo")
    public Result getComUserInfo(@RequestParam String userId) {
        SysUserComVo comUserInfo = sysUserService.getComUserInfo(userId);
        return Result.ok(comUserInfo);
    }

}
