package com.beneway.common.system.entity.sys_user.fo;

import com.beneway.common.common.utils.page.PageQuery;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Create by zhy on 2022/2/28 16:18
 */
@Data
public class SysUserQueryFo extends PageQuery {
    /**
     * 用户名
     */
    private String username;
    /**
     * 账号
     */
    private String account;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 班级
     */
    private String clazzId;
    /**
     * 科目id
     */
    private String subjectId;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
