package com.beneway.web.controller.work;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.beneway.common.common.result.Result;
import com.beneway.common.common.utils.ClassUtil;
import com.beneway.common.common.utils.LoginUserUtils;
import com.beneway.common.lyq.entity.work.Work;
import com.beneway.common.lyq.entity.work.fo.WorkFo;
import com.beneway.common.lyq.entity.work.fo.WorkQueryFo;
import com.beneway.common.lyq.entity.work.vo.WorkVo;
import com.beneway.common.lyq.service.work.WorkService;
import com.beneway.common.system.entity.sys_files.SysFiles;
import com.beneway.common.system.entity.sys_user.vo.LoginUserInfo;
import com.beneway.common.system.service.sys_files.SysFilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

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
