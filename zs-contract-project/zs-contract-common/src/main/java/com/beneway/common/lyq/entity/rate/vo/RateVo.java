package com.beneway.common.lyq.entity.rate.vo;

import com.beneway.common.lyq.entity.rate.Rate;
import com.beneway.common.system.entity.sys_files.SysFiles;
import lombok.Data;

import java.util.List;

/**
 * @author lichen
 * @email dcam00r0@qq.com
 * @date 2022/3/29 10:40
 */
@Data
public class RateVo extends Rate {
    /**
     * 文件列表
     */
    private List<SysFiles> filesList;
}
