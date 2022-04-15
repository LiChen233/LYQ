package com.lyq.app.service.means;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyq.app.dao.means.MeansDao;
import com.lyq.app.entity.means.Means;
import org.springframework.stereotype.Service;

/**
 * @author lichen
 * @email dcam00r0@qq.com
 * @date 2022/4/15 23:15
 */
@Service
public class MeansServiceImpl extends ServiceImpl<MeansDao, Means> implements MeansService {
}
