package com.lyq.controller.means;

import cn.hutool.Hutool;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
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

    @GetMapping("/getWeather")
    public Result getWeather(@RequestParam("city") String city){
        String s = HttpUtil.get("https://apis.juhe.cn/simpleWeather/query?city=" + city + "&key=5c54838de5438dc5a673b0bc647897ce");
        JSONObject json = JSONObject.parseObject(s);
        Integer code = json.getInteger("error_code");
        if (code==0){
            JSONObject result = json.getJSONObject("result");
            return Result.ok(result);
        }
        return Result.error("查询失败。请稍后再试吧");
    }
}
