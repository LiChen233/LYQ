package com.beneway.web.controller.topiccheck;

import com.beneway.common.common.result.Result;
import com.beneway.common.lyq.entity.topic.fo.TopicFo;
import com.beneway.common.lyq.entity.topic_check.TopicCheck;
import com.beneway.common.lyq.entity.topic_check.fo.TopicCheckFo;
import com.beneway.common.lyq.service.topic.TopicService;
import com.beneway.common.lyq.service.topiccheck.TopicCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author lichen
 * @email dcam00r0@qq.com
 * @date 2022/4/2 23:32
 */
@CrossOrigin
@RestController
@RequestMapping("/topiccheck")
public class TopicCheckController {

    @Autowired
    private TopicCheckService topicCheckService;

    /**
     * 插入学生的答案
     */
    @PostMapping("/")
    public Result insert(@RequestBody TopicCheckFo topicCheckFo) {
        return topicCheckService.insert(topicCheckFo);
    }

}
