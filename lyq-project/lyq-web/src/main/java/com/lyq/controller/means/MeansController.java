package com.lyq.controller.means;

import com.lyq.app.service.means.MeansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lichen
 * @email dcam00r0@qq.com
 * @date 2022/4/15 23:18
 */
@RestController
@RequestMapping("/means")
public class MeansController {
    @Autowired
    private MeansService meansService;
}
