package com.lyq.service.subject;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.lyq.common.result.Result;
import com.lyq.common.utils.LoginUserUtils;
import com.lyq.common.utils.page.PageUtils;
import com.lyq.dao.subject.SubjectDao;
import com.lyq.entity.subject.Subject;
import com.lyq.entity.subject.fo.SubjectFo;
import com.lyq.entity.subject.fo.SubjectQueryFo;
import com.lyq.entity.subject.vo.SubjectVo;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * 
 *
 * @author LiChen
 * @email dcam00r0@qq.com
 * @date 2022-03-28 21:13:28
 */
@Service("subjectService")
public class SubjectServiceImpl extends ServiceImpl<SubjectDao, Subject> implements SubjectService {

    @Autowired
    private SubjectDao subjectDao;

    @Override
    public Result list(SubjectQueryFo subjectQueryFo) {
        List<SubjectVo> vos = subjectDao.queryPage(subjectQueryFo);
        return Result.ok(vos);
    }

    @Override
    public Result queryPage(SubjectQueryFo subjectQueryFo) {
        Page page = PageUtils.getPage(subjectQueryFo);
        IPage<SubjectVo> iPage = subjectDao.queryPage(page, subjectQueryFo);
        return Result.ok(iPage);
    }

    @Override
    public Result insert(SubjectFo subjectFo) {
        subjectFo.setCreateTime(new Date())
                .setCreateUser(LoginUserUtils.getLoginUserId());
        save(subjectFo);
        return Result.ok(subjectFo.getId());
    }

    @Override
    public Result put(SubjectFo subjectFo) {
        subjectFo.setUpdateTime(new Date())
                .setUpdateUser(LoginUserUtils.getLoginUserId());
        updateById(subjectFo);
        return Result.ok();
    }

    @Override
    public Result del(String id) {
        LambdaUpdateWrapper<Subject> wrapper = new LambdaUpdateWrapper<Subject>()
                .eq(Subject::getId, id)
                .set(Subject::getDeleted, 1)
                .set(Subject::getUpdateTime, new Date())
                .set(Subject::getUpdateUser, LoginUserUtils.getLoginUserId());
        update(wrapper);
        return Result.ok();
    }
}
