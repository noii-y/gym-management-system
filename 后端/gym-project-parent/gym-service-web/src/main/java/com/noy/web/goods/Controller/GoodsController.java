package com.noy.web.goods.Controller;

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
import com.noy.web.goods.entity.Goods;
import com.noy.web.goods.entity.GoodsParam;
import com.noy.web.goods.service.GoodsService;

/**
 * 商品管理控制器
 * 
 * <p>提供商品相关的REST API接口，包括：</p>
 * <ul>
 *   <li>商品信息的增删改查操作</li>
 *   <li>商品列表的分页查询和条件筛选</li>
 *   <li>商品名称的模糊搜索功能</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
@RestController
@RequestMapping("/api/goods")
public class GoodsController {

    // ==================== 依赖注入 ====================
    
    /**
     * 商品业务逻辑服务
     */
    @Autowired
    private GoodsService goodsService;

    // ==================== 商品管理接口 ====================

    /**
     * 新增商品
     * 
     * <p>添加新的商品信息到系统中</p>
     * 
     * @param goods 商品信息对象
     * @return 新增操作结果
     */
    @PostMapping
    public ResultVo add(@RequestBody Goods goods) {
        if (goodsService.save(goods)) {
            return ResultUtils.success("新增成功");
        }
        return ResultUtils.error("新增失败!");
    }

    /**
     * 编辑商品
     * 
     * <p>更新已存在的商品信息</p>
     * 
     * @param goods 包含更新信息的商品对象
     * @return 编辑操作结果
     */
    @PutMapping
    public ResultVo edit(@RequestBody Goods goods) {
        if (goodsService.updateById(goods)) {
            return ResultUtils.success("编辑成功");
        }
        return ResultUtils.error("编辑失败!");
    }

    /**
     * 删除商品
     * 
     * <p>根据商品ID删除指定的商品信息</p>
     * 
     * @param goodsId 商品ID
     * @return 删除操作结果
     */
    @DeleteMapping("/{goodsId}")
    public ResultVo delete(@PathVariable("goodsId") Long goodsId) {
        if (goodsService.removeById(goodsId)) {
            return ResultUtils.success("删除成功");
        }
        return ResultUtils.error("删除失败!");
    }

    /**
     * 查询商品列表
     * 
     * <p>分页查询商品列表，支持按商品名称进行模糊搜索</p>
     * 
     * @param param 查询参数，包含分页信息和搜索条件
     * @return 商品列表查询结果
     */
    @GetMapping("/list")
    public ResultVo list(GoodsParam param) {
        // 构造分页对象
        IPage<Goods> page = new Page<>(param.getCurrentPage(), param.getPageSize());
        
        // 构造查询条件
        QueryWrapper<Goods> query = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(param.getName())) {
            query.lambda().like(Goods::getName, param.getName());
        }
        
        IPage<Goods> list = goodsService.page(page, query);
        return ResultUtils.success("查询成功", list);
    }
}
