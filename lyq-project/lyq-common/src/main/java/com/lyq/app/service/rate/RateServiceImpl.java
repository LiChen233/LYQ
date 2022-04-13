package com.lyq.app.service.rate;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyq.common.result.Result;
import com.lyq.common.utils.LoginUserUtils;
import com.lyq.app.dao.rate.RateDao;
import com.lyq.app.entity.rate.Rate;
import com.lyq.app.entity.rate.fo.RateFo;
import com.lyq.app.entity.topic.Topic;
import com.lyq.app.entity.topic_check.TopicCheck;
import com.lyq.app.service.topic.TopicService;
import com.lyq.app.service.topiccheck.TopicCheckService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lichen
 * @email dcam00r0@qq.com
 * @date 2022/4/5 17:52
 */
@Service("rateServiceImpl")
public class RateServiceImpl extends ServiceImpl<RateDao, Rate> implements RateService {

    @Autowired
    private TopicService topicService;

    @Autowired
    private TopicCheckService topicCheckService;

    /**
     * 进行分数判定
     *
     * @param rateFo
     * @return
     */
    @Override
    public Result insert(RateFo rateFo) {
        String userId = LoginUserUtils.getLoginUserId();
        LambdaQueryWrapper<Rate> rateW = new LambdaQueryWrapper<>();
        rateW.eq(Rate::getWorkId,rateFo.getWorkId())
                .eq(Rate::getUserId,userId);
        Rate rate = getOne(rateW);
        if (null != rate){
            return Result.error("此作业已经提交，无法重复提交");
        }
        LambdaQueryWrapper<Topic> topicLambdaQueryWrapper = new LambdaQueryWrapper<>();
        topicLambdaQueryWrapper.eq(Topic::getWorkId, rateFo.getWorkId());
        List<Topic> topics = topicService.list(topicLambdaQueryWrapper);
        double sum = 0;
        double size = topics.size();
        for (Topic topic : topics) {
            String rightKey = topic.getRightKey();
            if (StringUtils.isNotBlank(rightKey)) {
                LambdaQueryWrapper<TopicCheck> topicCheckLambdaQueryWrapper = new LambdaQueryWrapper<>();
                topicCheckLambdaQueryWrapper.eq(TopicCheck::getTopicId, topic.getId())
                        .eq(TopicCheck::getStudentId, userId);
                TopicCheck topicCheck = topicCheckService.getOne(topicCheckLambdaQueryWrapper);
                String myCheck = topicCheck.getMyCheck();
                if (rightKey.equals(myCheck)) {
                    sum++;
                }
            }else{
                size--;
            }
        }
        double auto = sum / size * 100;
        rateFo.setRateAuto((int) auto)
                .setUserId(userId);
        save(rateFo);
        return Result.ok(rateFo);
    }

    @Override
    public Result put(RateFo rateFo) {
        LambdaUpdateWrapper<Rate> wrapper = new LambdaUpdateWrapper<>();
        wrapper.set(Rate::getRate,rateFo.getRate())
                .eq(Rate::getWorkId,rateFo.getWorkId())
                .eq(Rate::getUserId,rateFo.getUserId());
        update(wrapper);
        return Result.ok();
    }
}
