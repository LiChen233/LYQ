package com.beneway.common.lyq.service.topic;

import com.baomidou.mybatisplus.extension.service.IService;
import com.beneway.common.common.result.Result;
import com.beneway.common.lyq.entity.topic.Topic;
import com.beneway.common.lyq.entity.topic.fo.TopicFo;
import com.beneway.common.lyq.entity.topic.fo.TopicQueryFo;

/**
 * 
 *
 * @author LiChen
 * @email dcam00r0@qq.com
 * @date 2022-03-28 21:13:27
 */
public interface TopicService extends IService<Topic> {

    Result queryPage(TopicQueryFo topicQueryFo);

    Result insert(TopicFo topicFo);

    Result list(String workId);
}

