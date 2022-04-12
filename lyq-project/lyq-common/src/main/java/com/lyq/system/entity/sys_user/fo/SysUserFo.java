package com.lyq.system.entity.sys_user.fo;

import com.lyq.system.entity.sys_user.SysUser;
import lombok.Data;

import java.util.List;

@Data
public class SysUserFo extends SysUser {

    /**
     * 角色
     */
    private List<Integer> roleList;
}
