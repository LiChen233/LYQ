package com.beneway.web.controller.clazz;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.beneway.common.common.result.Result;
import com.beneway.common.common.utils.LoginUserUtils;
import com.beneway.common.lyq.entity.clazz.Clazz;
import com.beneway.common.lyq.entity.clazz.fo.ClazzFo;
import com.beneway.common.lyq.entity.clazz.fo.ClazzQueryFo;
import com.beneway.common.lyq.entity.clazz.vo.ClazzVo;
import com.beneway.common.lyq.service.clazz.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 *
 * @author LiChen
 * @email dcam00r0@qq.com
 * @date 2022-03-28 21:13:28
 */
@RestController
@RequestMapping("/clazz")
public class ClazzController {
    @Autowired
    private ClazzService classService;

    /**
     * 查询
     */
    @GetMapping("/")
    public Result find(@RequestParam("id") String id){
        List<Clazz> list = new ArrayList<>();
        for (String s : id.split(",")) {
            Clazz clazz = classService.getById(s);
            list.add(clazz);
        }
        return Result.ok(list);
    }

    /**
     * 查询所有
     */
    @PostMapping("/list")
    public Result list(@RequestBody(required = false) ClazzQueryFo clazzQueryFo){
        return classService.list(clazzQueryFo);
    }

    /**
     * 查询我的班级
     */
    @GetMapping("/myList")
    public Result myList(){
        return classService.myList();
    }

    /**
     * 分页查询
     */
    @PostMapping("/queryPage")
    public Result queryPage(@RequestBody ClazzQueryFo clazzQueryFo){
        return classService.queryPage(clazzQueryFo);
    }


    /**
     * 插入
     */
    @PostMapping("/")
    public Result insert(@RequestBody ClazzFo clazzFo){
        return classService.insert(clazzFo);
    }

    /**
     * 修改
     */
    @PutMapping("/")
    public Result update(@RequestBody ClazzFo clazzFo){
        return classService.put(clazzFo);
    }

    /**
     * 删除
     */
    @DeleteMapping("/")
    public Result delete(@RequestParam("id") String id){
        return classService.del(id);
    }

}
