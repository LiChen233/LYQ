package com.beneway.common.lyq.service.answer;

import com.baomidou.mybatisplus.extension.service.IService;
import com.beneway.common.common.result.Result;
import com.beneway.common.lyq.entity.answer.Answer;
import com.beneway.common.lyq.entity.answer.fo.AnswerFo;
import com.beneway.common.lyq.entity.answer.fo.AnswerQueryFo;

/**
 * 
 *
 * @author LiChen
 * @email dcam00r0@qq.com
 * @date 2022-03-28 21:13:27
 */
public interface AnswerService extends IService<Answer> {

    Result queryPage(AnswerQueryFo answerQueryFo);

    Result insert(AnswerFo answerFo);

}

