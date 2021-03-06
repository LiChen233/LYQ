package com.lyq.system.service.sys_role;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyq.common.mybatisplus.MyIService;
import com.lyq.common.result.Result;
import com.lyq.system.entity.sys_role.SysRole;
import com.lyq.system.entity.sys_role.enums.SysRoleTypeEnum;
import com.lyq.system.entity.sys_role.fo.SysRoleFo;
import com.lyq.system.entity.sys_role.fo.SysRolePageQueryFo;
import com.lyq.system.entity.sys_role.vo.SysRoleVo;

import java.util.List;

/**
 *
 *
 * @author zhy
 * @email 2434017367@qq.com
 * @date 2022-03-01 14:59:40
 */
public interface SysRoleService extends MyIService<SysRole> {

    Result add(SysRoleFo sysRoleFo);

    Result edit(SysRoleFo SysRoleFo);

    Result del(Integer sysRoleId);

    Page<SysRoleVo> queryPage(SysRolePageQueryFo sysRolePageQueryFo);

    /**
     * 获取角色列表
     * @param type
     * @return
     */
    List<SysRole> getList(SysRoleTypeEnum type);

    /**
     * 根据用户id获取角色列表
     * @param userId
     * @return
     */
    List<SysRole> getListByUserId(String userId);

    /**
     * 根据用户id获取角色id列表
     * @param userId
     * @return
     */
    List<Integer> getIdListByUserId(String userId);


    /**
     * 根据用户id获取菜单id列表
     * @param userId
     * @return
     */
    List<Integer> getMenuIdListByUserId(String userId);

}

