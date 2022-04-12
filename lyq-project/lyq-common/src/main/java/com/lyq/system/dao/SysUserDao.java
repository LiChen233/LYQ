package com.lyq.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyq.system.entity.sys_user.SysUser;
import com.lyq.system.entity.sys_user.fo.SysUserQueryFo;
import com.lyq.system.entity.sys_user.vo.SysUserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SysUserDao extends BaseMapper<SysUser> {

    Page<SysUserVo> queryPage(Page page, @Param("params") SysUserQueryFo sysUserPageQueryFo);

}
