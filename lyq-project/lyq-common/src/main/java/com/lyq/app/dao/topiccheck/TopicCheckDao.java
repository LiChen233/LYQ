package com.lyq.app.dao.topiccheck;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lyq.app.entity.topic_check.TopicCheck;
import com.lyq.app.entity.topic_check.fo.TopicCheckQueryFo;
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
public interface TopicCheckDao extends BaseMapper<TopicCheck> {

    IPage<TopicCheck> queryPage(Page page, @Param("param") TopicCheckQueryFo topicCheckQueryFo);
}
