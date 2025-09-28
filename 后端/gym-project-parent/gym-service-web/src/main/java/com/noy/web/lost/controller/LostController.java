package com.noy.web.lost.controller;

// MyBatis Plus 相关
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

// Apache Commons 工具类
import org.apache.commons.lang.StringUtils;

// Spring 框架相关
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// 项目内部类
import com.noy.utils.ResultUtils;
import com.noy.utils.ResultVo;
import com.noy.web.lost.entity.Lost;
import com.noy.web.lost.entity.LostParam;
import com.noy.web.lost.service.LostService;

/**
 * 失物招领管理控制器
 * 
 * <p>提供失物招领相关的REST API接口，包括：</p>
 * <ul>
 *   <li>失物信息的增删改查操作</li>
 *   <li>失物列表的分页查询和条件筛选</li>
 *   <li>失物名称的模糊搜索功能</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
@RestController
@RequestMapping("/api/lost")
public class LostController {

    // ==================== 依赖注入 ====================
    
    /**
     * 失物招领业务逻辑服务
     */
    @Autowired
    private LostService lostService;

    // ==================== 失物招领管理接口 ====================

    /**
     * 新增失物信息
     * 
     * <p>添加新的失物招领信息到系统中</p>
     * 
     * @param lost 失物信息对象
     * @return 新增操作结果
     */
    @PostMapping
    public ResultVo add(@RequestBody Lost lost) {
        if (lostService.save(lost)) {
            return ResultUtils.success("插入成功!");
        }
        return ResultUtils.error("插入失败!");
    }

    /**
     * 编辑失物信息
     * 
     * <p>更新已存在的失物招领信息</p>
     * 
     * @param lost 包含更新信息的失物对象
     * @return 编辑操作结果
     */
    @PutMapping
    public ResultVo edit(@RequestBody Lost lost) {
        if (lostService.updateById(lost)) {
            return ResultUtils.success("编辑成功!");
        }
        return ResultUtils.error("编辑失败!");
    }

    /**
     * 删除失物信息
     * 
     * <p>根据失物ID删除指定的失物招领信息</p>
     * 
     * @param lostId 失物ID
     * @return 删除操作结果
     */
    @DeleteMapping("/{lostId}")
    public ResultVo delete(@PathVariable("lostId") Long lostId) {
        if (lostService.removeById(lostId)) {
            return ResultUtils.success("删除成功!");
        }
        return ResultUtils.error("删除失败!");
    }

    /**
     * 查询失物列表
     * 
     * <p>分页查询失物招领列表，支持按失物名称进行模糊搜索</p>
     * 
     * @param param 查询参数，包含分页信息和搜索条件
     * @return 失物列表查询结果
     */
    @GetMapping("/list")
    public ResultVo list(LostParam param) {
        // 构造分页对象
        IPage<Lost> page = new Page<>(param.getCurrentPage(), param.getPageSize());
        
        // 构造查询条件
        QueryWrapper<Lost> query = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(param.getLostName())) {
            query.lambda().like(Lost::getLostName, param.getLostName());
        }
        
        IPage<Lost> list = lostService.page(page, query);
        return ResultUtils.success("查询成功", list);
    }
}
