package com.beneway.common.lyq.entity.clazz.fo;

import com.beneway.common.common.utils.page.PageQuery;
import lombok.Data;

/**
 * @author lichen
 * @email dcam00r0@qq.com
 * @date 2022/3/29 10:36
 */
@Data
public class ClazzQueryFo extends PageQuery {
    /**
     * 班级名字
     */
    private String name;
}
