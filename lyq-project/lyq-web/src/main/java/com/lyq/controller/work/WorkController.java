package com.lyq.controller.work;

import com.lyq.common.result.Result;
import com.lyq.app.entity.work.fo.WorkFo;
import com.lyq.app.entity.work.fo.WorkQueryFo;
import com.lyq.app.service.work.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author lichen
 * @email dcam00r0@qq.com
 * @date 2022/4/2 23:32
 */
@CrossOrigin
@RestController
@RequestMapping("/work")
public class WorkController {

    @Autowired
    private WorkService workService;

    /**
     * 查询，如果有作业，则返回作业id
     * 如果没有，就创建一条，再返回
     */
    @PostMapping("/")
    public Result findAndInsert(@RequestBody WorkFo workFo) {
        return workService.findAndInsert(workFo);
    }

    /**
     * 分页查询
     */
    @PostMapping("/queryPage")
    public Result queryPage(@RequestBody WorkQueryFo workQueryFo) {
        return workService.queryPage(workQueryFo);
    }

    /**
     * 分页查询-我的作业
     */
    @PostMapping("/myWork")
    public Result myWork(@RequestBody WorkQueryFo workQueryFo) {
        return workService.myWork(workQueryFo);
    }

    /**
     * 删除
     */
    @DeleteMapping("/")
    public Result del(@RequestParam("id") String id) {
        return workService.del(id);
    }
}
