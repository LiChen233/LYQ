package com.lyq.service.notice;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.lyq.common.result.Result;
import com.lyq.common.utils.LoginUserUtils;
import com.lyq.common.utils.page.PageUtils;
import com.lyq.dao.notice.NoticeDao;
import com.lyq.entity.notice.Notice;
import com.lyq.entity.notice.emus.NoticeStatusEmus;
import com.lyq.entity.notice.fo.NoticeFo;
import com.lyq.entity.notice.fo.NoticeQueryFo;
import com.lyq.entity.notice.vo.NoticeVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author LiChen
 * @email dcam00r0@qq.com
 * @date 2022-03-31 19:39:42
 */
@Service("noticeService")
public class NoticeServiceImpl extends ServiceImpl<NoticeDao, Notice> implements NoticeService {

    @Autowired
    private NoticeDao noticeDao;

    @Override
    public Result queryPage(NoticeQueryFo noticeQueryFo) {
        Page page = PageUtils.getPage(noticeQueryFo);
        IPage<NoticeVo> iPage = noticeDao.queryPage(page, noticeQueryFo);
        return Result.ok(iPage);
    }

    @Override
    public Result insert(NoticeFo noticeFo) {
        noticeFo.setCreateTime(new Date())
                .setCreateUser(LoginUserUtils.getLoginUserId());
        save(noticeFo);
        return Result.ok(noticeFo.getId());
    }

    @Override
    public List<Notice> getNotice() {
        String clazzIds = LoginUserUtils.getLoginUserInfo().getClazzId();
        List<Notice> list = new ArrayList<>();
        if (StringUtils.isNotBlank(clazzIds)) {
            for (String clazzId : clazzIds.split(",")) {
                LambdaQueryWrapper<Notice> wrapper = new LambdaQueryWrapper<>();
                wrapper.eq(Notice::getStatus, NoticeStatusEmus.PUSH)
                        .and(e -> e.like(Notice::getClazzId, clazzId)
                                .or()
                                .isNull(Notice::getClazzId));
                list.addAll(list(wrapper));
            }
        }
        return list.stream().distinct().collect(Collectors.toList());
    }

    @Override
    public void push(String id) {
        change(id, NoticeStatusEmus.PUSH);
    }

    @Override
    public void off(String id) {
        change(id, NoticeStatusEmus.OFF);
    }

    @Override
    public void del(String id) {
        change(id, NoticeStatusEmus.DEL);
    }

    @Override
    public void put(NoticeFo noticeFo) {
        noticeFo.setUpdateTime(new Date())
                .setUpdateUser(LoginUserUtils.getLoginUserId())
                .setStatus(null);
        updateById(noticeFo);
    }

    private void change(String id, NoticeStatusEmus noticeStatusEmus) {
        if (StringUtils.isNotBlank(id)) {
            LambdaUpdateWrapper<Notice> wrapper = new LambdaUpdateWrapper<>();
            wrapper.eq(Notice::getId, id)
                    .set(Notice::getStatus, noticeStatusEmus);
            update(wrapper);
        }

    }
}
