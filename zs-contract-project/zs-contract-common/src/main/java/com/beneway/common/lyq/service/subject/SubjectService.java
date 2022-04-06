package com.beneway.common.lyq.service.subject;

import com.baomidou.mybatisplus.extension.service.IService;
import com.beneway.common.common.result.Result;
import com.beneway.common.lyq.entity.clazz.fo.ClazzFo;
import com.beneway.common.lyq.entity.clazz.fo.ClazzQueryFo;
import com.beneway.common.lyq.entity.subject.Subject;
import com.beneway.common.lyq.entity.subject.fo.SubjectFo;
import com.beneway.common.lyq.entity.subject.fo.SubjectQueryFo;

/**
 * 
 *
 * @author LiChen
 * @email dcam00r0@qq.com
 * @date 2022-03-28 21:13:28
 */
public interface SubjectService extends IService<Subject> {

    Result list(SubjectQueryFo subjectQueryFo);

    Result queryPage(SubjectQueryFo subjectQueryFo);

    Result insert(SubjectFo subjectFo);

    Result put(SubjectFo subjectFo);

    Result del(String id);
}

