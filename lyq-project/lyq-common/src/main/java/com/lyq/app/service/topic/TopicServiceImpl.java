package com.lyq.app.service.topic;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lyq.app.entity.topic.fo.TopicFo;
import com.lyq.app.entity.topic.vo.TopicVo;
import com.lyq.app.service.topiccheck.TopicCheckService;
import com.lyq.common.result.Result;
import com.lyq.common.utils.ClassUtil;
import com.lyq.common.utils.LoginUserUtils;
import com.lyq.common.utils.page.PageUtils;
import com.lyq.app.dao.topic.TopicDao;
import com.lyq.app.entity.topic.Topic;
import com.lyq.app.entity.topic.fo.TopicQueryFo;
import com.lyq.app.entity.topic_check.TopicCheck;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

/**
 * @author LiChen
 * @email dcam00r0@qq.com
 * @date 2022-03-28 21:13:27
 */
@Service("topicService")
public class TopicServiceImpl extends ServiceImpl<TopicDao, Topic> implements TopicService {

    @Autowired
    private TopicDao topicDao;

    @Autowired
    private TopicCheckService topicCheckService;

    @Override
    public Result queryPage(TopicQueryFo topicQueryFo) {
        Page page = PageUtils.getPage(topicQueryFo);
        IPage<Topic> iPage = topicDao.queryPage(page, topicQueryFo);
        return Result.ok(iPage);
    }

    @Override
    public Result insert(TopicFo topicFo) {
        save(topicFo);
        return Result.ok(topicFo.getId());
    }

    @Override
    public Result list(String workId, String userId) {
        LambdaQueryWrapper<Topic> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Topic::getWorkId, workId);
        List<Topic> topicList = list(wrapper);
        List<TopicVo> topicVos = ClassUtil.toClassList(topicList, TopicVo.class);
        if (StringUtils.isBlank(userId)){
            userId = LoginUserUtils.getLoginUserId();
        }
        //同时查询这个人的答案
        if (null != topicVos && topicVos.size() > 0) {
            for (TopicVo topicVo : topicVos) {
                LambdaQueryWrapper<TopicCheck> queryWrapper = new LambdaQueryWrapper<>();
                queryWrapper.eq(TopicCheck::getTopicId,topicVo.getId())
                                .eq(TopicCheck::getStudentId,userId);
                TopicCheck check = topicCheckService.getOne(queryWrapper);
                if (null != check){
                    String myCheck = check.getMyCheck();
                    if (myCheck.length()>1){
                        List<String> list = Arrays.asList(myCheck.split(","));
                        topicVo.setMyCheck(list);
                    }else {
                        topicVo.setMyCheck(myCheck);
                    }
                }
            }
        }
        return Result.ok(topicVos);
    }
}
