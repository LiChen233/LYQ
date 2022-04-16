package com.lyq.app.service.means;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyq.app.entity.means.Means;
import com.lyq.app.entity.means.fo.MeansFo;
import com.lyq.common.result.Result;

/**
 * @author lichen
 * @email dcam00r0@qq.com
 * @date 2022/4/15 23:15
 */
public interface MeansService extends IService<Means> {
    Result getMy();

    Result getAll();

    Result addAndUpdate(MeansFo meansFo);
}
