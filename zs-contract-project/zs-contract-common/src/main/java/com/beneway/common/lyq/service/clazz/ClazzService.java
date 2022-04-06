package com.beneway.common.lyq.service.clazz;

import com.baomidou.mybatisplus.extension.service.IService;
import com.beneway.common.common.result.Result;
import com.beneway.common.lyq.entity.clazz.Clazz;
import com.beneway.common.lyq.entity.clazz.fo.ClazzFo;
import com.beneway.common.lyq.entity.clazz.fo.ClazzQueryFo;

/**
 * 
 *
 * @author LiChen
 * @email dcam00r0@qq.com
 * @date 2022-03-28 21:13:28
 */
public interface ClazzService extends IService<Clazz> {

    Result list(ClazzQueryFo clazzQueryFo);

    Result queryPage(ClazzQueryFo clazzQueryFo);

    Result insert(ClazzFo clazzFo);

    Result put(ClazzFo clazzFo);

    Result del(String id);

    Result myList();
}

