package com.lyq.app.entity.timetable;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author lichen
 * @email dcam00r0@qq.com
 * @date 2022/4/15 23:08
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("timetable")
public class Timetable implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;
    /**
     * 班级id
     */
    private String clazzId;
    /**
     * 课表文件id
     */
    private String files;
}
