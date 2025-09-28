package com.noy.web.suggest.controller;

// Java 标准库
import java.util.Date;

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
import com.noy.web.suggest.entity.Suggest;
import com.noy.web.suggest.entity.SuggestParam;
import com.noy.web.suggest.service.SuggestService;

/**
 * 建议反馈管理控制器
 * 
 * <p>提供建议反馈相关的REST API接口，包括：</p>
 * <ul>
 *   <li>建议反馈信息的增删改查操作</li>
 *   <li>建议反馈列表的分页查询和条件筛选</li>
 *   <li>建议反馈标题的模糊搜索功能</li>
 *   <li>按时间倒序排列显示</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
@RestController
@RequestMapping("/api/suggest")
public class SuggestController {

    // ==================== 依赖注入 ====================
    
    /**
     * 建议反馈业务逻辑服务
     */
    @Autowired
    private SuggestService suggestService;

    // ==================== 建议反馈管理接口 ====================
    
    /**
     * 新增建议反馈
     * 
     * <p>添加新的建议反馈信息到系统中，自动设置提交时间</p>
     * 
     * @param suggest 建议反馈信息对象
     * @return 新增操作结果
     */
    @PostMapping
    public ResultVo add(@RequestBody Suggest suggest) {
        suggest.setDateTime(new Date());
        if (suggestService.save(suggest)) {
            return ResultUtils.success("反馈成功!");
        }
        return ResultUtils.error("反馈失败!");
    }

    /**
     * 编辑建议反馈信息
     * 
     * <p>根据建议反馈ID更新建议反馈信息</p>
     * 
     * @param suggest 建议反馈信息对象，包含要更新的数据
     * @return 编辑操作结果
     */
    @PutMapping
    public ResultVo edit(@RequestBody Suggest suggest) {
        if (suggestService.updateById(suggest)) {
            return ResultUtils.success("编辑成功!");
        }
        return ResultUtils.error("编辑失败!");
    }

    /**
     * 删除建议反馈信息
     * 
     * <p>根据建议反馈ID删除指定的建议反馈信息</p>
     * 
     * @param id 建议反馈ID
     * @return 删除操作结果
     */
    @DeleteMapping("/{id}")
    public ResultVo delete(@PathVariable("id") Long id) {
        if (suggestService.removeById(id)) {
            return ResultUtils.success("删除成功!");
        }
        return ResultUtils.error("删除失败!");
    }

    /**
     * 查询建议反馈列表
     * 
     * <p>分页查询建议反馈列表，支持按标题进行模糊搜索，按时间倒序排列</p>
     * 
     * @param param 查询参数，包含分页信息和搜索条件
     * @return 建议反馈列表查询结果
     */
    @GetMapping("/list")
    public ResultVo list(SuggestParam param) {
        // 构造分页对象
        IPage<Suggest> page = new Page<>(
                param.getCurrentPage(), param.getPageSize());
        
        // 构造查询条件
        QueryWrapper<Suggest> query = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(param.getTitle())) {
            query.lambda().like(Suggest::getTitle, param.getTitle());
        }
        
        // 按时间倒序排列
        query.lambda().orderByDesc(Suggest::getDateTime);
        
        IPage<Suggest> list = suggestService.page(page, query);
        return ResultUtils.success("查询成功", list);
    }
}
