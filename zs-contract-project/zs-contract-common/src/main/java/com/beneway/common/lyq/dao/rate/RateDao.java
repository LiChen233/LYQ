package com.beneway.common.lyq.dao.rate;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beneway.common.lyq.entity.rate.Rate;
import com.beneway.common.lyq.entity.rate.fo.RateQueryFo;
import com.beneway.common.lyq.entity.rate.vo.RateVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 
 *
 * @author LiChen
 * @email dcam00r0@qq.com
 * @date 2022-03-28 21:13:27
 */
@Mapper
@Repository
public interface RateDao extends BaseMapper<Rate> {
}
