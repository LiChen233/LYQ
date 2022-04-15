package com.lyq.app.dao.means;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lyq.app.entity.means.Means;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author lichen
 * @email dcam00r0@qq.com
 * @date 2022/4/15 23:14
 */
@Mapper
@Repository
public interface MeansDao extends BaseMapper<Means> {
}
