package com.lyq.system.service.sys_user.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyq.system.entity.sys_user.SysUserRole;
import com.lyq.system.service.sys_user.SysUserRoleService;
import com.lyq.system.dao.SysUserRoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

/**
 * Create by zhy on 2022/3/2 10:02
 */
@Service("sysUserRoleService")
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleDao, SysUserRole> implements SysUserRoleService {

    @Autowired
    private SysUserRoleDao sysUserRoleDao;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addUserRole(String userId, List<Integer> roleIdList) {
        if (null != roleIdList){
            List<SysUserRole> list = new LinkedList<>();
            for (Integer roleId : roleIdList) {
                SysUserRole sysUserRole = new SysUserRole();
                sysUserRole.setSysUserId(userId);
                sysUserRole.setSysRoleId(roleId);
                list.add(sysUserRole);
            }
            this.saveBatch(list);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateUserRole(String userId, List<Integer> roleIdList) {
        removeUserRole(userId);
        addUserRole(userId, roleIdList);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void removeUserRole(String userId) {
        this.remove(new LambdaQueryWrapper<SysUserRole>().eq(SysUserRole::getSysUserId, userId));
    }

}
