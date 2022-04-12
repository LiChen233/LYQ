package com.lyq.dao.subject;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lyq.entity.subject.Subject;
import com.lyq.entity.subject.fo.SubjectQueryFo;
import com.lyq.entity.subject.vo.SubjectVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 *
 * @author LiChen
 * @email dcam00r0@qq.com
 * @date 2022-03-28 21:13:28
 */
@Mapper
@Repository
public interface SubjectDao extends BaseMapper<Subject> {

    IPage<SubjectVo> queryPage(Page page, @Param("param") SubjectQueryFo subjectQueryFo);

    List<SubjectVo> queryPage(@Param("param") SubjectQueryFo subjectQueryFo);
}
