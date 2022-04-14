package com.lyq.app.service.work;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyq.app.entity.work.Work;
import com.lyq.app.entity.work.fo.WorkQueryFo;
import com.lyq.common.result.Result;
import com.lyq.app.entity.work.fo.WorkFo;

/**
 * 
 *
 * @author LiChen
 * @email dcam00r0@qq.com
 * @date 2022-03-28 21:13:27
 */
public interface WorkService extends IService<Work> {

    Result queryPage(WorkQueryFo workQueryFo);

    Result insert(WorkFo workFo);

    Result findAndInsert(WorkFo workFo);

    Result del(String id);

    Result myWork(WorkQueryFo workQueryFo);

    Result getWorkDone(WorkQueryFo workQueryFo);

    Result upload(WorkFo workFo);
}

