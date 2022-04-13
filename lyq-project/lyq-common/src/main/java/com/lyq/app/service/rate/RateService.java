package com.lyq.app.service.rate;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyq.common.result.Result;
import com.lyq.app.entity.rate.Rate;
import com.lyq.app.entity.rate.fo.RateFo;

/**
 * @author lichen
 * @email dcam00r0@qq.com
 * @date 2022/4/5 17:52
 */
public interface RateService extends IService<Rate> {
    Result insert(RateFo rateFo);

    Result put(RateFo rateFo);
}
