package com.lyq.controller.timetable;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lyq.app.entity.timetable.Timetable;
import com.lyq.app.entity.timetable.fo.TimetableFo;
import com.lyq.app.service.timetable.TimetableService;
import com.lyq.common.result.Result;
import com.lyq.common.utils.LoginUserUtils;
import com.lyq.system.entity.sys_user.vo.LoginUserInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author lichen
 * @email dcam00r0@qq.com
 * @date 2022/4/15 23:17
 */
@RestController
@RequestMapping("/timetable")
public class TimetableController {
    @Autowired
    private TimetableService timetableService;

    @GetMapping("/")
    public Result getMy(){
        LoginUserInfo userInfo = LoginUserUtils.getLoginUserInfo();
        String clazzId = userInfo.getClazzId();
        if (StringUtils.isNotBlank(clazzId)){
            clazzId = clazzId.split(",")[0];
            LambdaQueryWrapper<Timetable> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Timetable::getClazzId,clazzId);
            Timetable timetable = timetableService.getOne(wrapper);
            return Result.ok(timetable.getFiles());
        }else {
            return Result.ok(null);
        }
    }

    @PostMapping("/")
    public Result insert(@RequestBody TimetableFo timetableFo){
        LambdaQueryWrapper<Timetable> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Timetable::getClazzId,timetableFo.getClazzId());
        Timetable one = timetableService.getOne(wrapper);
        if (null != one){
            one.setFiles(timetableFo.getFiles());
            timetableService.updateById(one);
            return Result.ok(one.getId());
        }
        timetableService.save(timetableFo);
        return Result.ok(timetableFo.getId());
    }
}
