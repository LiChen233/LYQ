package com.lyq.app.service.subject;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyq.app.entity.subject.fo.SubjectFo;
import com.lyq.common.result.Result;
import com.lyq.app.entity.subject.Subject;
import com.lyq.app.entity.subject.fo.SubjectQueryFo;

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

