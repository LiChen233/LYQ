package com.lyq.app.dao.answer;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lyq.app.entity.answer.vo.AnswerVo;
import com.lyq.app.entity.answer.Answer;
import com.lyq.app.entity.answer.fo.AnswerQueryFo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * 
 *
 * @author LiChen
 * @email dcam00r0@qq.com
 * @date 2022-03-28 21:13:27
 */
@Mapper
@Repository
public interface AnswerDao extends BaseMapper<Answer> {

    IPage<AnswerVo> queryPage(Page page, @Param("param") AnswerQueryFo answerQueryFo);
}
