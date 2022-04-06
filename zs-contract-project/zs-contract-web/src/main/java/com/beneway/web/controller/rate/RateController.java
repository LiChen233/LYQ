package com.beneway.web.controller.rate;

import com.beneway.common.common.result.Result;
import com.beneway.common.lyq.entity.rate.fo.RateFo;
import com.beneway.common.lyq.service.rate.RateService;
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

}
