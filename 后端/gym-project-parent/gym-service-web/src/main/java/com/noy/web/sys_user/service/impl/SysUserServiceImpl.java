package com.noy.web.sys_user.service.impl;

// MyBatis Plus 相关
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

// Apache Commons 工具类
import org.apache.commons.lang.StringUtils;

// Spring 框架相关
import org.springframework.stereotype.Service;

// 项目内部类
import com.noy.web.sys_user.entity.PageParam;
import com.noy.web.sys_user.entity.SysUser;
import com.noy.web.sys_user.mapper.SysUserMapper;
import com.noy.web.sys_user.service.SysUserService;

/**
 * 系统用户服务实现类
 * 
 * <p>提供系统用户相关的业务逻辑处理，包括：</p>
 * <ul>
 *   <li>用户信息的分页查询</li>
 *   <li>用户认证支持</li>
 *   <li>用户信息管理</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    // ==================== 业务方法 ====================

    /**
     * 根据条件分页查询用户列表
     * 
     * <p>支持按昵称和手机号模糊查询</p>
     * 
     * @param param 查询参数，包含分页信息和查询条件
     * @return 分页的用户数据
     */
    @Override
    public IPage<SysUser> list(PageParam param) {
        // 构造分页对象
        IPage<SysUser> page = new Page<>();
        page.setSize(param.getPageSize());
        page.setCurrent(param.getCurrentPage());
        
        // 构造查询条件
        QueryWrapper<SysUser> query = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(param.getNickName())) {
            query.lambda().like(SysUser::getNickName, param.getNickName());
        }
        if (StringUtils.isNotEmpty(param.getPhone())) {
            query.lambda().like(SysUser::getPhone, param.getPhone());
        }
        
        return this.baseMapper.selectPage(page, query);
    }

    /**
     * 根据用户名查询用户信息
     * 
     * <p>用于用户认证和登录验证</p>
     * 
     * @param username 用户名
     * @return 用户信息，如果不存在则返回null
     */
    @Override
    public SysUser loadUser(String username) {
        QueryWrapper<SysUser> query = new QueryWrapper<>();
        query.lambda().eq(SysUser::getUsername, username);
        return this.baseMapper.selectOne(query);
    }
}
