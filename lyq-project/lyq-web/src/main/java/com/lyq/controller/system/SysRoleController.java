package com.lyq.controller.system;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lyq.common.result.Result;
import com.lyq.system.entity.sys_role.SysRole;
import com.lyq.system.service.sys_role.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhy
 * @email 2434017367@qq.com
 * @date 2022/3/26 11:48
 */
@RestController
@RequestMapping("sys_role")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 获取前端公共回显组件信息
     * @param id
     * @return
     */
    @GetMapping("/getComRoleInfo")
    public Result getComRoleInfo(@RequestParam Integer id){
        SysRole sysRole = sysRoleService.getOne(new LambdaQueryWrapper<SysRole>()
                .select(SysRole::getType, SysRole::getTitle, SysRole::getRemark)
                .eq(SysRole::getId, id));
        return Result.ok(sysRole);
    }

}
