package com.lyq.app.service.notice;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyq.app.entity.notice.Notice;
import com.lyq.app.entity.notice.fo.NoticeFo;
import com.lyq.app.entity.notice.fo.NoticeQueryFo;
import com.lyq.common.result.Result;

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

