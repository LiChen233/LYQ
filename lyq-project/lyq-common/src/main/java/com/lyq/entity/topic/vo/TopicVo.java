package com.lyq.entity.topic.vo;

import com.lyq.entity.topic.Topic;
import lombok.Data;

/**
 * @author lichen
 * @email dcam00r0@qq.com
 * @date 2022/3/29 10:38
 */
@Data
public class TopicVo extends Topic {
    /**
     * 我的选择
     */
    private Object myCheck;
}
