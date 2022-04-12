package com.lyq.system.service.sys_user.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyq.common.result.Result;
import com.lyq.common.utils.ClassUtil;
import com.lyq.common.utils.page.PageUtils;
import com.lyq.entity.clazz.Clazz;
import com.lyq.service.clazz.ClazzService;
import com.lyq.system.dao.SysUserDao;
import com.lyq.system.entity.sys_user.SysUser;
import com.lyq.system.entity.sys_user.fo.SysUserFo;
import com.lyq.system.entity.sys_user.fo.SysUserQueryFo;
import com.lyq.system.entity.sys_user.vo.LoginUserInfo;
import com.lyq.system.entity.sys_user.vo.SysUserComVo;
import com.lyq.system.entity.sys_user.vo.SysUserVo;
import com.lyq.system.service.sys_role.SysRoleService;
import com.lyq.system.service.sys_user.SysUserRoleService;
import com.lyq.system.service.sys_user.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@EnableAspectJAutoProxy( proxyTargetClass = true , exposeProxy = true )
@CacheConfig(cacheNames = "sysUser")
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUser> implements SysUserService {

    @Autowired
    private SysUserDao sysUserDao;


    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private ClazzService clazzService;


    private SysUserService getCurrThis(){
        SysUserService currentProxy = (SysUserService) AopContext.currentProxy();
        return currentProxy;
    }

    @Cacheable(key = "'list'")
    @Override
    public List<SysUser> list(){
        return super.list(new LambdaQueryWrapper<SysUser>()
                .orderByAsc(SysUser::getCreateTime));
    }

    /**
     * 密码加密
     * @param password
     * @return
     */
    @Override
    public String passEncr(String password) {
        if (StrUtil.isNotEmpty(password)){
            return DigestUtil.md5Hex(password);
        }else{
            return null;
        }
    }

    @CacheEvict(key = "'list'", allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result add(SysUserFo sysUserFo) {
        sysUserFo.setPassword(passEncr(sysUserFo.getPassword()));
        sysUserFo.setCreateTime(new Date());
        this.save(sysUserFo);

        List<Integer> roleList = sysUserFo.getRoleList();
        sysUserRoleService.addUserRole(sysUserFo.getId(), roleList);

        return Result.ok();
    }

    @CacheEvict(key = "'list'", allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result edit(SysUserFo sysUserFo) {
        sysUserFo.setPassword(passEncr(sysUserFo.getPassword()));
        this.updateById(sysUserFo);

        List<Integer> roleList = sysUserFo.getRoleList();
        sysUserRoleService.updateUserRole(sysUserFo.getId(), roleList);

        return Result.ok();
    }

    @CacheEvict(key = "'list'", allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result del(String userId) {
        this.removeById(userId);
        sysUserRoleService.removeUserRole(userId);
        return Result.ok();
    }

    @Override
    public LoginUserInfo getLoginUserInfo(String userId) {
        SysUser sysUser = this.getById(userId);
        return ClassUtil.toClass(sysUser, LoginUserInfo.class);
    }

    @Override
    public boolean isPermission(String userId, String permission) {
        return false;
    }

    @Override
    public Page<SysUserVo> queryPage(SysUserQueryFo sysUserPageQueryFo) {
        Page page = PageUtils.getPage(sysUserPageQueryFo);

        Page<SysUserVo> voPage = sysUserDao.queryPage(page, sysUserPageQueryFo);
        List<SysUserVo> records = voPage.getRecords();
        for (SysUserVo record : records) {
            packVo(record);
        }

        return voPage;
    }

    @Override
    public SysUserVo getUserInfo(String userId) {
        SysUser sysUser = this.getById(userId);
        return packVo(sysUser);
    }

    private SysUserVo packVo(SysUser sysUser){
        return ClassUtil.toClass(sysUser, SysUserVo.class);
    }

    private SysUserVo packVo(SysUserVo sysUserVo) {
        List<Integer> roleList = sysRoleService.getIdListByUserId(sysUserVo.getId());
        sysUserVo.setRoleList(roleList);
        String clazzId = sysUserVo.getClazzId();
        if (StringUtils.isNotBlank(clazzId)){
            List<String> ids = Arrays.asList(clazzId.split(","));
            sysUserVo.setClazzList(ids);
            List<Clazz> clazzList = clazzService.listByIds(ids);
            List<String> names = new ArrayList<>();
            for (Clazz clazz : clazzList) {
                names.add(clazz.getName());
            }
            sysUserVo.setClazzNameList(names);
        }else{
            sysUserVo.setClazzList(new ArrayList<>());
        }
        return sysUserVo;
    }

    /**
     * 获取前端统一组件的用户信息
     * @return
     */
    @Override
    public SysUserComVo getComUserInfo(String userId) {
        SysUser sysUser = this.getOne(new LambdaQueryWrapper<SysUser>()
                .select(SysUser::getUsername)
                .eq(SysUser::getId, userId));
        SysUserComVo sysUserComVo = ClassUtil.toClass(sysUser, SysUserComVo.class);
        return sysUserComVo;
    }

}
