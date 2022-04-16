package com.lyq.controller.means;

import com.lyq.app.entity.means.fo.MeansFo;
import com.lyq.app.service.means.MeansService;
import com.lyq.common.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author lichen
 * @email dcam00r0@qq.com
 * @date 2022/4/15 23:18
 */
@RestController
@RequestMapping("/means")
public class MeansController {
    @Autowired
    private MeansService meansService;

    /**
     * 获取我的资料
     */
    @GetMapping("/")
    public Result getMy(){
        return meansService.getMy();
    }

    @PostMapping("/")
    public Result addAndUpdate(@RequestBody MeansFo meansFo){
        return meansService.addAndUpdate(meansFo);
    }

    /**
     * 获取所有资料
     */
    @GetMapping("/getAll")
    public Result getAll(){
        return meansService.getAll();
    }
}
