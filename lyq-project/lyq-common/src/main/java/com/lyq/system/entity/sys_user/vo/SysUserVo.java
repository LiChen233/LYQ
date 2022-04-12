package com.lyq.system.entity.sys_user.vo;

import com.lyq.system.entity.sys_user.SysUser;
import lombok.Data;

import java.util.List;

/**
 * Create by zhy on 2022/2/28 17:41
 */
@Data
public class SysUserVo extends SysUser {

    /**
     * 权限
     */
    private List<Integer> roleList;
    /**
     * 班级名称列表
     */
    private List<String> clazzNameList;
    /**
     * 班级id列表
     */
    private List<String> clazzList;
    /**
     * 科目
     */
    private String subjectName;
}
