package com.lyq.controller.topiccheck;

import com.lyq.common.result.Result;
import com.lyq.entity.topic_check.fo.TopicCheckFo;
import com.lyq.service.topiccheck.TopicCheckService;
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
