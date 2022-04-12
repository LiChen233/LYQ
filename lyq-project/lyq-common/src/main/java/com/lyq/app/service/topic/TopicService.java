package com.lyq.app.service.topic;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyq.app.entity.topic.fo.TopicFo;
import com.lyq.common.result.Result;
import com.lyq.app.entity.topic.Topic;
import com.lyq.app.entity.topic.fo.TopicQueryFo;

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

