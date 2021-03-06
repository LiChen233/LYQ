package com.lyq.system.service.sys_menu;

import com.lyq.common.mybatisplus.MyIService;
import com.lyq.common.result.Result;
import com.lyq.system.entity.sys_menu.SysMenu;
import com.lyq.system.entity.sys_menu.fo.SysMenuFo;
import com.lyq.system.entity.sys_menu.vo.SysMenuVo;

import java.util.List;

/**
 *
 *
 * @author zhy
 * @email 2434017367@qq.com
 * @date 2022-02-23 11:44:57
 */
public interface SysMenuService extends MyIService<SysMenu> {

    Result add(SysMenuFo sysMenuFo);

    Result edit(SysMenuFo sysMenuFo);

    Result del(Integer id);

    List<SysMenuVo> getTreeList();

    List<SysMenuVo> getUserMenuList();

}

