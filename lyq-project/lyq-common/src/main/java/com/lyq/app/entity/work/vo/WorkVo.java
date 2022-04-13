package com.lyq.app.entity.work.vo;

import com.lyq.app.entity.rate.Rate;
import com.lyq.app.entity.work.Work;
import com.lyq.system.entity.sys_files.SysFiles;
import lombok.Data;

import java.util.List;

/**
 * @author lichen
 * @email dcam00r0@qq.com
 * @date 2022/3/29 10:40
 */
@Data
public class WorkVo extends Work {
    /**
     * 班级名称
     */
    private String clazzName;
    /**
     * 科目名称
     */
    private String subjectName;
    /**
     * 发布人
     */
    private String teacherName;
    /**
     * 文件列表
     */
    private List<SysFiles> filesList;
    /**
     * 评分
     */
    private Rate rate;
    /**
     * 姓名
     */
    private String username;
    /**
     * 手机号
     */
    private String phone;
}
