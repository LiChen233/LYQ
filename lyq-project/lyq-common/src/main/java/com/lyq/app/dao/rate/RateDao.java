package com.lyq.app.dao.rate;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lyq.app.entity.rate.Rate;
import org.apache.ibatis.annotations.Mapper;
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
