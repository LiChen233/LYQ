package com.lyq.app.entity.subject.vo;

import com.lyq.app.entity.subject.Subject;
import lombok.Data;

/**
 * @author lichen
 * @email dcam00r0@qq.com
 * @date 2022/3/29 10:37
 */
@Data
public class SubjectVo extends Subject {
    /**
     * 创建人名字
     */
    private String createMan;
    /**
     * 创建人名字
     */
    private String updateMan;
    /**
     * 文件
     */
    private String files;
}
