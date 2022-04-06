package com.beneway.common.lyq.service.work;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.beneway.common.common.result.Result;
import com.beneway.common.common.utils.ClassUtil;
import com.beneway.common.common.utils.LoginUserUtils;
import com.beneway.common.common.utils.page.PageUtils;
import com.beneway.common.lyq.dao.work.WorkDao;
import com.beneway.common.lyq.entity.rate.Rate;
import com.beneway.common.lyq.entity.topic.Topic;
import com.beneway.common.lyq.entity.work.fo.WorkFo;
import com.beneway.common.lyq.entity.work.fo.WorkQueryFo;
import com.beneway.common.lyq.entity.work.Work;
import com.beneway.common.lyq.entity.work.vo.WorkVo;
import com.beneway.common.lyq.service.rate.RateService;
import com.beneway.common.lyq.service.topic.TopicService;
import com.beneway.common.system.entity.sys_files.SysFiles;
import com.beneway.common.system.entity.sys_user.vo.LoginUserInfo;
import com.beneway.common.system.service.sys_files.SysFilesService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 
 *
 * @author LiChen
 * @email dcam00r0@qq.com
 * @date 2022-03-28 21:13:27
 */
@Service("workService")
public class WorkServiceImpl extends ServiceImpl<WorkDao, Work> implements WorkService {

    @Autowired
    private WorkDao workDao;

    @Autowired
    private SysFilesService sysFilesService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private RateService rateService;

    @Override
    public Result queryPage(WorkQueryFo workQueryFo) {
        Page page = PageUtils.getPage(workQueryFo);
        IPage<WorkVo> iPage = workDao.queryPage(page, workQueryFo);
        return Result.ok(iPage);
    }

    @Override
    public Result myWork(WorkQueryFo workQueryFo) {
        Page page = PageUtils.getPage(workQueryFo);
        LoginUserInfo info = LoginUserUtils.getLoginUserInfo();
        String userId = info.getId();
        String clazzId = info.getClazzId();
        if (StringUtils.isNotBlank(clazzId)){
            String[] split = clazzId.split(",");
            workQueryFo.setClazzId(split[0]);
        }
        IPage<WorkVo> iPage = workDao.queryPage(page, workQueryFo);
        for (WorkVo workVo : iPage.getRecords()) {
            //获取我是否完成
            LambdaQueryWrapper<Rate> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Rate::getWorkId,workVo.getId())
                    .eq(Rate::getUserId,userId);
            Rate rate = rateService.getOne(wrapper);
            workVo.setRate(rate);
        }
        return Result.ok(iPage);
    }

    @Override
    public Result insert(WorkFo workFo) {
        save(workFo);
        return Result.ok();
    }

    @Override
    public Result findAndInsert(WorkFo workFo) {
        LoginUserInfo userInfo = LoginUserUtils.getLoginUserInfo();
        String userId = userInfo.getId();
        String subjectId = userInfo.getSubjectId();
        LambdaQueryWrapper<Work> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Work::getClazzId, workFo.getClazzId())
                .eq(Work::getTeacherId, userId)
                .eq(Work::getSubjectId, subjectId)
                .eq(Work::getTime, workFo.getTime());
        Work work = getOne(wrapper);
        if (null != work) {
            List<SysFiles> filesList = sysFilesService.getListByIds(work.getFiles());
            WorkVo vo = ClassUtil.toClass(work, WorkVo.class);
            vo.setFilesList(filesList);
            return Result.ok(vo);
        } else {
            workFo.setCreateTime(new Date())
                    .setId(null)
                    .setTeacherId(userId)
                    .setSubjectId(subjectId);
            save(workFo);
            return Result.ok(workFo);
        }
    }

    @Transactional
    @Override
    public Result del(String id) {
        removeById(id);
        LambdaQueryWrapper<Topic> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Topic::getWorkId,id);
        topicService.remove(wrapper);
        return Result.ok();
    }
}
