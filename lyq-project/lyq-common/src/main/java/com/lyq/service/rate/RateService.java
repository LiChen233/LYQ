package com.lyq.service.rate;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyq.common.result.Result;
import com.lyq.entity.rate.Rate;
import com.lyq.entity.rate.fo.RateFo;

/**
 * @author lichen
 * @email dcam00r0@qq.com
 * @date 2022/4/5 17:52
 */
public interface RateService extends IService<Rate> {
    Result insert(RateFo rateFo);
}
