package com.lyq.dao.work;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lyq.entity.work.fo.WorkQueryFo;
import com.lyq.entity.work.Work;
import com.lyq.entity.work.vo.WorkVo;
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
public interface WorkDao extends BaseMapper<Work> {

    IPage<WorkVo> queryPage(Page page, @Param("param") WorkQueryFo workQueryFo);
}
