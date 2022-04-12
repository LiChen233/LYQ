package com.lyq.service.answer;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyq.common.result.Result;
import com.lyq.entity.answer.Answer;
import com.lyq.entity.answer.fo.AnswerFo;
import com.lyq.entity.answer.fo.AnswerQueryFo;

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

