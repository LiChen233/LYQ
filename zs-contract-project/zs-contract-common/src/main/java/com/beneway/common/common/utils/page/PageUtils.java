package com.beneway.common.common.utils.page;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beneway.common.common.result.Result;

import java.util.Map;

/**
 * Create by zhy on 2022/2/28 16:56
 *
 * 分页工具类
 */
public class PageUtils {

    public static Page getPage(PageQuery pageQuery){
        Page page = new Page(pageQuery.getPage(), pageQuery.getSize());
        return page;
    }

    public static Page getPage(Map<String, Object> param){
        int page = Integer.valueOf((String) param.get("page"));
        int size = Integer.valueOf((String) param.get("size"));
        return new Page(page, size);
    }
}
