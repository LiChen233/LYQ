package com.lyq.system.service.sys_role.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyq.common.result.Result;
import com.lyq.common.utils.page.PageUtils;
import com.lyq.system.dao.SysRoleDao;
import com.lyq.system.entity.sys_role.SysRole;
import com.lyq.system.entity.sys_role.enums.SysRoleTypeEnum;
import com.lyq.system.entity.sys_role.fo.SysRoleFo;
import com.lyq.system.entity.sys_role.fo.SysRolePageQueryFo;
import com.lyq.system.entity.sys_role.vo.SysRoleVo;
import com.lyq.system.entity.sys_user.SysUserRole;
import com.lyq.system.service.sys_role.SysRoleMenuService;
import com.lyq.system.service.sys_role.SysRoleService;
import com.lyq.system.service.sys_user.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 *
 * @author zhy
 * @email 2434017367@qq.com
 * @date 2022-03-01 14:59:40
 */
@Service("sysRoleService")
public class SysRoleServiceImpl extends ServiceImpl<SysRoleDao, SysRole> implements SysRoleService {

    @Autowired
    private SysRoleDao sysRoleDao;

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result add(SysRoleFo sysRoleFo) {
        this.save(sysRoleFo);
        List<Integer> sysMenuIdList = sysRoleFo.getSysMenuIdList();
        sysRoleMenuService.addRoleMenu(sysRoleFo.getId(), sysMenuIdList);
        return Result.ok();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result edit(SysRoleFo sysRoleFo) {
        this.updateById(sysRoleFo);
        List<Integer> sysMenuIdList = sysRoleFo.getSysMenuIdList();
        sysRoleMenuService.updateRoleMenu(sysRoleFo.getId(), sysMenuIdList);
        return Result.ok();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result del(Integer sysRoleId) {
        this.removeById(sysRoleId);
        sysRoleMenuService.removeRoleMenu(sysRoleId);
        return Result.ok();
    }

    @Override
    public Page<SysRoleVo> queryPage(SysRolePageQueryFo sysRolePageQueryFo) {
        Page page = PageUtils.getPage(sysRolePageQueryFo);
        Page<SysRoleVo> voPage = sysRoleDao.queryPage(page, sysRolePageQueryFo);
        List<SysRoleVo> records = voPage.getRecords();
        for (SysRoleVo record : records) {
            List<Integer> menuIdList = sysRoleMenuService.getMenuIdList(record.getId());
            record.setSysMenuIdList(menuIdList);
        }
        return voPage;
    }

    @Override
    public List<SysRole> getList(SysRoleTypeEnum type) {
        List<SysRole> list = this.list(new LambdaQueryWrapper<SysRole>().eq(type != null, SysRole::getType, type));
        return list;
    }

    @Override
    public List<SysRole> getListByUserId(String userId) {
        List<Integer> roleIdList = getIdListByUserId(userId);
        if (CollUtil.isNotEmpty(roleIdList)){
            List<SysRole> roleList = this.listByIds(roleIdList);
            return roleList;
        }else{
            return new ArrayList<>();
        }
    }

    @Override
    public List<Integer> getIdListByUserId(String userId) {
        List<SysUserRole> list = sysUserRoleService.list(new LambdaQueryWrapper<SysUserRole>()
                .select(SysUserRole::getSysRoleId)
                .eq(SysUserRole::getSysUserId, userId));
        List<Integer> roleIdList = list.stream().map(SysUserRole::getSysRoleId).collect(Collectors.toList());
        return roleIdList;
    }

    @Override
    public List<Integer> getMenuIdListByUserId(String userId) {
        List<Integer> roleIdList = getIdListByUserId(userId);
        List<Integer> menuIdList = new LinkedList<>();
        for (Integer roleId : roleIdList) {
            List<Integer> list = sysRoleMenuService.getMenuIdList(roleId);
            menuIdList.addAll(list);
        }
        menuIdList = menuIdList.stream().distinct().collect(Collectors.toList());
        return menuIdList;
    }

}
