package com.lyq.app.service.topiccheck;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyq.app.entity.topic_check.fo.TopicCheckFo;
import com.lyq.common.result.Result;
import com.lyq.app.entity.topic_check.TopicCheck;

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

