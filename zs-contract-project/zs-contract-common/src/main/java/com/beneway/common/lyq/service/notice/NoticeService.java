package com.beneway.common.lyq.service.notice;

import com.baomidou.mybatisplus.extension.service.IService;
import com.beneway.common.common.result.Result;
import com.beneway.common.lyq.entity.notice.Notice;
import com.beneway.common.lyq.entity.notice.fo.NoticeFo;
import com.beneway.common.lyq.entity.notice.fo.NoticeQueryFo;

import java.util.List;

/**
 * 
 *
 * @author LiChen
 * @email dcam00r0@qq.com
 * @date 2022-03-31 19:39:42
 */
public interface NoticeService extends IService<Notice> {

    Result queryPage(NoticeQueryFo noticeQueryFo);

    Result insert(NoticeFo noticeFo);

    List<Notice> getNotice();

    void push(String id);

    void off(String id);

    void del(String id);

    void put(NoticeFo noticeFo);
}

