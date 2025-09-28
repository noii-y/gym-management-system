package com.noy.web.lost.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.noy.web.lost.entity.Lost;

/**
 * 失物招领数据访问层接口
 * 
 * <p>继承MyBatis Plus的BaseMapper，提供失物招领相关的数据库操作，包括：</p>
 * <ul>
 *   <li>基础的CRUD操作（增删改查）</li>
 *   <li>条件查询和分页查询</li>
 *   <li>批量操作支持</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
public interface LostMapper extends BaseMapper<Lost> {
}
