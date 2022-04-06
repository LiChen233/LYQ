package com.beneway.common.lyq.service.clazz;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.beneway.common.common.result.Result;
import com.beneway.common.common.utils.LoginUserUtils;
import com.beneway.common.common.utils.page.PageUtils;
import com.beneway.common.lyq.dao.clazz.ClazzDao;
import com.beneway.common.lyq.entity.clazz.Clazz;
import com.beneway.common.lyq.entity.clazz.fo.ClazzFo;
import com.beneway.common.lyq.entity.clazz.fo.ClazzQueryFo;
import com.beneway.common.lyq.entity.clazz.vo.ClazzVo;
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
}
