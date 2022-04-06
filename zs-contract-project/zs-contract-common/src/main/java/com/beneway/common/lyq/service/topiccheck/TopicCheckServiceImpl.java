package com.beneway.common.lyq.service.topiccheck;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.beneway.common.common.result.Result;
import com.beneway.common.common.utils.LoginUserUtils;
import com.beneway.common.common.utils.page.PageUtils;
import com.beneway.common.lyq.dao.topiccheck.TopicCheckDao;
import com.beneway.common.lyq.entity.topic_check.TopicCheck;
import com.beneway.common.lyq.entity.topic_check.fo.TopicCheckFo;
import com.beneway.common.lyq.entity.topic_check.fo.TopicCheckQueryFo;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 *
 * @author LiChen
 * @email dcam00r0@qq.com
 * @date 2022-03-28 21:13:27
 */
@Service("topicCheckService")
public class TopicCheckServiceImpl extends ServiceImpl<TopicCheckDao, TopicCheck> implements TopicCheckService {

    @Autowired
    private TopicCheckDao topicCheckDao;

    @Override
    public Result insert(TopicCheckFo topicCheckFo) {
        String userId = LoginUserUtils.getLoginUserId();
        topicCheckFo.setStudentId(userId);
        LambdaQueryWrapper<TopicCheck> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TopicCheck::getTopicId,topicCheckFo.getTopicId())
                .eq(TopicCheck::getStudentId, userId);
        TopicCheck topicCheck = getOne(wrapper);
        if (null != topicCheck){
            topicCheck.setMyCheck(topicCheckFo.getMyCheck());
            updateById(topicCheck);
        }else{
            save(topicCheckFo);
        }
        return Result.ok(topicCheckFo.getId());
    }
}
