package com.lyq.app.entity.work.fo;

import com.lyq.common.utils.page.PageQuery;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author lichen
 * @email dcam00r0@qq.com
 * @date 2022/3/29 10:39
 */
@Data
public class WorkQueryFo extends PageQuery {
    /**
     * 姓名
     */
    private String username;
    /**
     * 班级
     */
    private String clazzId;
    /**
     * 作业id
     */
    private String workId;
    /**
     * 时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String time;
    /**
     * 自己的id
     */
    private String userId;
}
