package com.beneway.web.controller.subject;

import com.beneway.common.common.result.Result;
import com.beneway.common.lyq.entity.clazz.fo.ClazzQueryFo;
import com.beneway.common.lyq.entity.subject.Subject;
import com.beneway.common.lyq.entity.subject.fo.SubjectFo;
import com.beneway.common.lyq.entity.subject.fo.SubjectQueryFo;
import com.beneway.common.lyq.service.subject.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 
 *
 * @author LiChen
 * @email dcam00r0@qq.com
 * @date 2022-03-28 21:13:28
 */
@CrossOrigin
@RestController
@RequestMapping("/subject")
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    /**
     * 查询
     */
    @GetMapping("/")
    public Result find(@RequestParam("id") String id){
        Subject subject = subjectService.getById(id);
        return Result.ok(subject);
    }

    /**
     * 查询所有
     */
    @PostMapping("/list")
    public Result list(@RequestBody(required = false) SubjectQueryFo subjectQueryFo){
        return subjectService.list(subjectQueryFo);
    }

    /**
     * 分页查询
     */
    @PostMapping("/queryPage")
    public Result queryPage(@RequestBody SubjectQueryFo subjectQueryFo){
        return subjectService.queryPage(subjectQueryFo);
    }


    /**
     * 插入
     */
    @PostMapping("/")
    public Result insert(@RequestBody SubjectFo subjectFo){
        return subjectService.insert(subjectFo);
    }

    /**
     * 修改
     */
    @PutMapping("/")
    public Result update(@RequestBody SubjectFo subjectFo){
        return subjectService.put(subjectFo);
    }

    /**
     * 删除
     */
    @DeleteMapping("/")
    public Result delete(@RequestParam("id") String id){
        return subjectService.del(id);
    }

}
