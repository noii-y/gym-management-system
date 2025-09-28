package com.noy.web.lost.service.impl;

// MyBatis Plus 相关
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

// Spring 框架相关
import org.springframework.stereotype.Service;

// 项目内部类
import com.noy.web.lost.entity.Lost;
import com.noy.web.lost.mapper.LostMapper;
import com.noy.web.lost.service.LostService;

/**
 * 失物招领服务实现类
 * 
 * <p>提供失物招领相关的业务逻辑处理，包括：</p>
 * <ul>
 *   <li>失物信息的基础CRUD操作</li>
 *   <li>继承MyBatis Plus的通用服务方法</li>
 *   <li>支持分页查询和条件筛选</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
@Service
public class LostServiceImpl extends ServiceImpl<LostMapper, Lost> implements LostService {
    
    // 继承ServiceImpl，提供基础的CRUD操作
    // 如需要自定义业务逻辑，可在此处添加方法实现
}
