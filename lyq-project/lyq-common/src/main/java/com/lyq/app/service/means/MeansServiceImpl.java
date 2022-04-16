package com.lyq.app.service.means;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyq.app.dao.means.MeansDao;
import com.lyq.app.entity.means.Means;
import com.lyq.app.entity.means.fo.MeansFo;
import com.lyq.app.entity.subject.Subject;
import com.lyq.app.service.subject.SubjectService;
import com.lyq.common.result.Result;
import com.lyq.common.utils.LoginUserUtils;
import com.lyq.system.entity.sys_user.SysUser;
import com.lyq.system.entity.sys_user.vo.LoginUserInfo;
import com.lyq.system.service.sys_user.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lichen
 * @email dcam00r0@qq.com
 * @date 2022/4/15 23:15
 */
@Service
public class MeansServiceImpl extends ServiceImpl<MeansDao, Means> implements MeansService {

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private SysUserService sysUserService;

    @Override
    public Result getMy() {
        LoginUserInfo info = LoginUserUtils.getLoginUserInfo();
        LambdaQueryWrapper<Means> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Means::getSubjectId, info.getSubjectId())
                .eq(Means::getTeacherId, info.getId());
        Means means = getOne(wrapper);
        if (null != means) {
            return Result.ok(means.getFiles());
        } else {
            return Result.ok();
        }
    }

    @Override
    public Result getAll() {
        List<Subject> subjects = subjectService.list();
        Map<String, Map<String, List<String>>> all = new HashMap<>();
        for (Subject subject : subjects) {
            LambdaQueryWrapper<Means> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Means::getSubjectId, subject.getId())
                    .last("GROUP BY teacher_id");
            List<Means> means = list(wrapper);
            Map<String, List<String>> map = new HashMap<>();
            Map<String, SysUser> userMap = new HashMap<>();
            for (Means mean : means) {
                String teacherId = mean.getTeacherId();
                SysUser teacher = userMap.get(teacherId);
                if (null == teacher) {
                    teacher = sysUserService.getById(teacherId);
                    userMap.put(teacherId, teacher);
                }
                String key = teacher.getUsername() + "," + teacher.getId();
                List<String> list = map.computeIfAbsent(key, k -> new ArrayList<>());
                list.add(mean.getFiles());
            }
            if (map.isEmpty()){
                map = null;
            }
            all.put(subject.getName(), map);
        }
        return Result.ok(all);
    }

    @Override
    public Result addAndUpdate(MeansFo meansFo) {
        LoginUserInfo info = LoginUserUtils.getLoginUserInfo();
        String subjectId = info.getSubjectId();
        String id = info.getId();

        LambdaQueryWrapper<Means> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Means::getSubjectId, subjectId)
                .eq(Means::getTeacherId, id);
        Means means = getOne(wrapper);
        if (null==means){
            means = Means.builder()
                    .teacherId(id)
                    .subjectId(subjectId)
                    .files(meansFo.getFiles())
                    .build();
            save(means);
        }else{
            means.setFiles(meansFo.getFiles());
            updateById(means);
        }
        return Result.ok();
    }
}
