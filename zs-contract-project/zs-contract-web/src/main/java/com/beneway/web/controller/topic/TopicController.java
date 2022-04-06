package com.beneway.web.controller.topic;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.beneway.common.common.result.Result;
import com.beneway.common.lyq.entity.topic.Topic;
import com.beneway.common.lyq.entity.topic.fo.TopicFo;
import com.beneway.common.lyq.entity.work.fo.WorkFo;
import com.beneway.common.lyq.entity.work.fo.WorkQueryFo;
import com.beneway.common.lyq.service.topic.TopicService;
import com.beneway.common.lyq.service.work.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
