package com.lyq.app.entity.clazz.vo;

import com.lyq.app.entity.clazz.Clazz;
import lombok.Data;

/**
 * @author lichen
 * @email dcam00r0@qq.com
 * @date 2022/3/29 10:37
 */
@Data
public class ClazzVo extends Clazz {
    /**
     * 创建人名字
     */
    private String createMan;
    /**
     * 创建人名字
     */
    private String updateMan;
    /**
     * 课表文件id
     */
    private String files;
}
