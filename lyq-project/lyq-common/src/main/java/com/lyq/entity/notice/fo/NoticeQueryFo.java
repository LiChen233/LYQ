package com.lyq.entity.notice.fo;

import com.lyq.common.utils.page.PageQuery;
import lombok.Data;

/**
 * @author lichen
 * @email dcam00r0@qq.com
 * @date 2022/3/29 10:37
 */
@Data
public class NoticeQueryFo extends PageQuery {
    /**
     * 标题
     */
    private String title;
}
