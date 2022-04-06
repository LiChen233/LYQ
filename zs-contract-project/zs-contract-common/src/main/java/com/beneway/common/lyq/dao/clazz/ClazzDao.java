package com.beneway.common.lyq.dao.clazz;

import com.beneway.common.lyq.entity.clazz.Clazz;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.beneway.common.lyq.entity.clazz.fo.ClazzQueryFo;
import com.beneway.common.lyq.entity.clazz.vo.ClazzVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author LiChen
 * @email dcam00r0@qq.com
 * @date 2022-03-28 21:13:28
 */
@Mapper
@Repository
public interface ClazzDao extends BaseMapper<Clazz> {

    IPage<ClazzVo> queryPage(Page page, @Param("param") ClazzQueryFo clazzQueryFo);

    List<ClazzVo> queryPage (@Param("param") ClazzQueryFo clazzQueryFo);
}
