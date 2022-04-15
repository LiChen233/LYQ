package com.lyq.app.service.clazz;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.lyq.app.entity.clazz.fo.ClazzFo;
import com.lyq.app.entity.clazz.vo.ClazzVo;
import com.lyq.app.entity.timetable.Timetable;
import com.lyq.app.service.timetable.TimetableService;
import com.lyq.common.result.Result;
import com.lyq.common.utils.LoginUserUtils;
import com.lyq.common.utils.page.PageUtils;
import com.lyq.app.dao.clazz.ClazzDao;
import com.lyq.app.entity.clazz.Clazz;
import com.lyq.app.entity.clazz.fo.ClazzQueryFo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 *
 * @author LiChen
 * @email dcam00r0@qq.com
 * @date 2022-03-28 21:13:28
 */
@Service("classService")
public class ClazzServiceImpl extends ServiceImpl<ClazzDao, Clazz> implements ClazzService {

    @Autowired
    private ClazzDao clazzDao;

    @Autowired
    private TimetableService timetableService;

    @Override
    public Result list(ClazzQueryFo clazzQueryFo) {
        List<ClazzVo> vos = clazzDao.queryPage(clazzQueryFo);
        return Result.ok(vos);
    }

    @Override
    public Result queryPage(ClazzQueryFo clazzQueryFo) {
        Page page = PageUtils.getPage(clazzQueryFo);
        IPage<ClazzVo> iPage = clazzDao.queryPage(page, clazzQueryFo);
        return Result.ok(iPage);
    }

    @Override
    public Result insert(ClazzFo clazzFo) {
        clazzFo.setCreateTime(new Date())
                        .setCreateUser(LoginUserUtils.getLoginUserId());
        save(clazzFo);
        return Result.ok(clazzFo.getId());
    }

    @Override
    public Result put(ClazzFo clazzFo) {
        clazzFo.setUpdateTime(new Date())
                .setUpdateUser(LoginUserUtils.getLoginUserId());
        updateById(clazzFo);
        return Result.ok();
    }

    @Override
    public Result del(String id) {
        LambdaUpdateWrapper<Clazz> wrapper = new LambdaUpdateWrapper<Clazz>()
                .eq(Clazz::getId, id)
                .set(Clazz::getDeleted, 1)
                .set(Clazz::getUpdateTime, new Date())
                .set(Clazz::getUpdateUser, LoginUserUtils.getLoginUserId());
        update(wrapper);
        return Result.ok();
    }

    @Override
    public Result myList() {
        List<Clazz> list = new ArrayList<>();
        String clazzId = LoginUserUtils.getLoginUserInfo().getClazzId();
        if (StringUtils.isNotBlank(clazzId)){
            for (String id : clazzId.split(",")) {
                list.add(getById(id));
            }
        }
        return Result.ok(list);
    }

    @Override
    public Result queryPageAndTimetable(ClazzQueryFo clazzQueryFo) {
        Page page = PageUtils.getPage(clazzQueryFo);
        IPage<ClazzVo> iPage = clazzDao.queryPage(page, clazzQueryFo);
        for (ClazzVo record : iPage.getRecords()) {
            LambdaQueryWrapper<Timetable> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Timetable::getClazzId, record.getId());
            Timetable one = timetableService.getOne(wrapper);
            if (null != one){
                record.setFiles(one.getFiles());
            }
        }
        return Result.ok(iPage);
    }
}
