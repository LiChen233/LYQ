package com.lyq.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lyq.system.entity.sys_files.SysFiles;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SysFilesDao extends BaseMapper<SysFiles> {

}
