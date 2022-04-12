package com.lyq.service.topiccheck;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyq.common.result.Result;
import com.lyq.entity.topic_check.TopicCheck;
import com.lyq.entity.topic_check.fo.TopicCheckFo;

/**
 * 
 *
 * @author LiChen
 * @email dcam00r0@qq.com
 * @date 2022-03-28 21:13:27
 */
public interface TopicCheckService extends IService<TopicCheck> {

    Result insert(TopicCheckFo topicCheckFo);

}

