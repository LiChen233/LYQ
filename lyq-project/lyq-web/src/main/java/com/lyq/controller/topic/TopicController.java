package com.lyq.controller.topic;

import com.lyq.common.result.Result;
import com.lyq.app.entity.topic.fo.TopicFo;
import com.lyq.app.service.topic.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author lichen
 * @email dcam00r0@qq.com
 * @date 2022/4/2 23:32
 */
@CrossOrigin
@RestController
@RequestMapping("/topic")
public class TopicController {

    @Autowired
    private TopicService topicService;

    /**
     * 插入题目
     */
    @PostMapping("/")
    public Result insert(@RequestBody TopicFo topicFo) {
        return topicService.insert(topicFo);
    }

    /**
     * 根据作业id查询下面的所有题目
     * @return
     */
    @GetMapping("/list")
    public Result list(@RequestParam("workId") String workId){
        return topicService.list(workId);
    }
}
