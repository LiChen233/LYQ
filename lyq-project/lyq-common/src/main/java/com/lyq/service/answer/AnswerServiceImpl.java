package com.lyq.service.answer;

import com.lyq.common.result.Result;
import com.lyq.common.utils.page.PageUtils;
import com.lyq.dao.answer.AnswerDao;
import com.lyq.entity.answer.Answer;
import com.lyq.entity.answer.fo.AnswerFo;
import com.lyq.entity.answer.fo.AnswerQueryFo;
import com.lyq.entity.answer.vo.AnswerVo;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 *
 * @author LiChen
 * @email dcam00r0@qq.com
 * @date 2022-03-28 21:13:27
 */
@Service("answerService")
public class AnswerServiceImpl extends ServiceImpl<AnswerDao, Answer> implements AnswerService {

    @Autowired
    private AnswerDao answerDao;

    @Override
    public Result queryPage(AnswerQueryFo answerQueryFo) {
        Page page = PageUtils.getPage(answerQueryFo);
        IPage<AnswerVo> iPage = answerDao.queryPage(page, answerQueryFo);
        return Result.ok(iPage);
    }

    @Override
    public Result insert(AnswerFo answerFo) {
        save(answerFo);
        return Result.ok(answerFo.getId());
    }
}
