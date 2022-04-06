package com.beneway.common.lyq.entity.topic.vo;

import com.beneway.common.lyq.entity.topic.Topic;
import lombok.Data;

import java.util.List;

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
