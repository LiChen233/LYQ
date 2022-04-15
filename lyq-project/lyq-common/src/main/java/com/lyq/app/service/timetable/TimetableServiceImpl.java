package com.lyq.app.service.timetable;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyq.app.dao.timetable.TimetableDao;
import com.lyq.app.entity.timetable.Timetable;
import org.springframework.stereotype.Service;

/**
 * @author lichen
 * @email dcam00r0@qq.com
 * @date 2022/4/15 23:16
 */
@Service
public class TimetableServiceImpl extends ServiceImpl<TimetableDao, Timetable> implements TimetableService {
}
