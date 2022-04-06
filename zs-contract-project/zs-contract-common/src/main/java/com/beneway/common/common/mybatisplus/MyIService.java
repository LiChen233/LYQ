package com.beneway.common.common.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface MyIService<T> extends IService<T> {

    default boolean isExist(Wrapper<T> queryWrapper){
        return count(queryWrapper) > 0;
    }

}
