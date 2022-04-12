package com.lyq.dao.topic;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lyq.entity.topic.Topic;
import com.lyq.entity.topic.fo.TopicQueryFo;
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
public interface TopicDao extends BaseMapper<Topic> {

    IPage<Topic> queryPage(Page page, @Param("param") TopicQueryFo topicQueryFo);
}
