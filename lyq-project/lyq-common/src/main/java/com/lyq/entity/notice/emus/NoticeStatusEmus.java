package com.lyq.entity.notice.emus;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author lichen
 * @email dcam00r0@qq.com
 * @date 2022/3/31 20:40
 */
public enum NoticeStatusEmus {
    // N：新增
    NEW("N"),
    // 发布
    PUSH("P"),
    // C：下架
    OFF("O"),
    // C：下架
    DEL("D");

    @EnumValue
    @JsonValue
    private final String status;

    NoticeStatusEmus(String status) {
        this.status = status;
    }
}
