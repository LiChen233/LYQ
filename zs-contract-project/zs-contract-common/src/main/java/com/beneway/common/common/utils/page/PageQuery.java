package com.beneway.common.common.utils.page;

import lombok.Data;

import java.io.Serializable;

/**
 * Create by zhy on 2022/2/28 13:52
 *
 * 分页查询参数类
 */
@Data
public class PageQuery implements Serializable {

    private int page = 1;

    private int size = 10;

}
