package com.lyq.app.dao.notice;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lyq.app.entity.notice.Notice;
import com.lyq.app.entity.notice.fo.NoticeQueryFo;
import com.lyq.app.entity.notice.vo.NoticeVo;
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
 * @date 2022-03-31 19:39:42
 */
@Mapper
@Repository
public interface NoticeDao extends BaseMapper<Notice> {

    IPage<NoticeVo> queryPage(Page page, @Param("param") NoticeQueryFo noticeQueryFo);
}
