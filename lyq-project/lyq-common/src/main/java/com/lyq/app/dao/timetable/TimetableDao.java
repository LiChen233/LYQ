package com.lyq.app.dao.timetable;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lyq.app.entity.timetable.Timetable;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author lichen
 * @email dcam00r0@qq.com
 * @date 2022/4/15 23:13
 */
@Mapper
@Repository
public interface TimetableDao extends BaseMapper<Timetable> {
}
