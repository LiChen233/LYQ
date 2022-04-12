package com.lyq.app.service.topiccheck;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lyq.app.entity.topic_check.fo.TopicCheckFo;
import com.lyq.common.result.Result;
import com.lyq.common.utils.LoginUserUtils;
import com.lyq.app.dao.topiccheck.TopicCheckDao;
import com.lyq.app.entity.topic_check.TopicCheck;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
