package com.beneway.common.lyq.service.rate;

import com.baomidou.mybatisplus.extension.service.IService;
import com.beneway.common.common.result.Result;
import com.beneway.common.lyq.entity.rate.Rate;
import com.beneway.common.lyq.entity.rate.fo.RateFo;

/**
 * @author lichen
 * @email dcam00r0@qq.com
 * @date 2022/4/5 17:52
 */
public interface RateService extends IService<Rate> {
    Result insert(RateFo rateFo);
}
