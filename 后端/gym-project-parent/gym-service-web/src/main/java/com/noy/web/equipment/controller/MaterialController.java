package com.noy.web.equipment.controller;

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
import com.noy.web.equipment.entity.ListParam;
import com.noy.web.equipment.entity.Material;
import com.noy.web.equipment.service.MaterialService;

/**
 * 器材管理控制器
 * 
 * <p>提供健身器材相关的REST API接口，包括：</p>
 * <ul>
 *   <li>器材信息的增删改查操作</li>
 *   <li>器材列表的分页查询和条件筛选</li>
 *   <li>器材名称的模糊搜索功能</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
@RestController
@RequestMapping("/api/material")
public class MaterialController {

    // ==================== 依赖注入 ====================
    
    /**
     * 器材业务逻辑服务
     */
    @Autowired
    private MaterialService materialService;

    // ==================== 器材管理接口 ====================

    /**
     * 新增器材
     * 
     * <p>添加新的健身器材信息到系统中</p>
     * 
     * @param material 器材信息对象
     * @return 新增操作结果
     */
    @PostMapping
    public ResultVo add(@RequestBody Material material) {
        if (materialService.save(material)) {
            return ResultUtils.success("新增成功!");
        }
        return ResultUtils.error("新增失败!");
    }

    /**
     * 编辑器材
     * 
     * <p>更新已存在的器材信息</p>
     * 
     * @param material 包含更新信息的器材对象
     * @return 编辑操作结果
     */
    @PutMapping
    public ResultVo edit(@RequestBody Material material) {
        if (materialService.updateById(material)) {
            return ResultUtils.success("编辑成功!");
        }
        return ResultUtils.error("编辑失败!");
    }

    /**
     * 删除器材
     * 
     * <p>根据器材ID删除指定的器材信息</p>
     * 
     * @param id 器材ID
     * @return 删除操作结果
     */
    @DeleteMapping("/{id}")
    public ResultVo delete(@PathVariable("id") Long id) {
        if (materialService.removeById(id)) {
            return ResultUtils.success("删除成功!");
        }
        return ResultUtils.error("删除失败!");
    }

    /**
     * 查询器材列表
     * 
     * <p>分页查询器材列表，支持按器材名称进行模糊搜索</p>
     * 
     * @param param 查询参数，包含分页信息和搜索条件
     * @return 器材列表查询结果
     */
    @GetMapping("/list")
    public ResultVo list(ListParam param) {
        // 构造分页对象
        IPage<Material> page = new Page<>(param.getCurrentPage(), param.getPageSize());
        
        // 构造查询条件
        QueryWrapper<Material> query = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(param.getName())) {
            query.lambda().like(Material::getName, param.getName());
        }
        
        IPage<Material> list = materialService.page(page, query);
        return ResultUtils.success("查询成功", list);
    }
}
