package com.lyq.controller.rate;

import com.lyq.common.result.Result;
import com.lyq.app.entity.rate.fo.RateFo;
import com.lyq.app.service.rate.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author lichen
 * @email dcam00r0@qq.com
 * @date 2022/4/2 23:32
 */
@CrossOrigin
@RestController
@RequestMapping("/rate")
public class RateController {

    @Autowired
    private RateService rateService;

    @PostMapping("/")
    public Result insert(@RequestBody RateFo rateFo){
        return rateService.insert(rateFo);
    }

    @PutMapping("/")
    public Result put(@RequestBody RateFo rateFo){
        return rateService.put(rateFo);
    }

}
