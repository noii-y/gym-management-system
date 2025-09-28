package com.noy.web.member_card.controller;

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
import com.noy.web.member_card.entity.ListCard;
import com.noy.web.member_card.entity.MemberCard;
import com.noy.web.member_card.service.MemberCardService;

/**
 * 会员卡管理控制器
 * 
 * <p>提供会员卡相关的REST API接口，包括：</p>
 * <ul>
 *   <li>会员卡信息的增删改查操作</li>
 *   <li>会员卡列表的分页查询和条件筛选</li>
 *   <li>会员卡标题的模糊搜索功能</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
@RestController
@RequestMapping("/api/memberCard")
public class MemberCardController {

    // ==================== 依赖注入 ====================
    
    /**
     * 会员卡业务逻辑服务
     */
    @Autowired
    private MemberCardService memberCardService;

    // ==================== 会员卡管理接口 ====================
    
    /**
     * 新增会员卡信息
     * 
     * <p>添加新的会员卡信息到系统中</p>
     * 
     * @param memberCard 会员卡信息对象
     * @return 新增操作结果
     */
    @PostMapping
    public ResultVo add(@RequestBody MemberCard memberCard) {
        if (memberCardService.save(memberCard)) {
            return ResultUtils.success("新增成功!");
        }
        return ResultUtils.error("新增失败!");
    }

    /**
     * 编辑会员卡信息
     * 
     * <p>根据会员卡ID更新会员卡信息</p>
     * 
     * @param memberCard 会员卡信息对象，包含要更新的数据
     * @return 编辑操作结果
     */
    @PutMapping
    public ResultVo edit(@RequestBody MemberCard memberCard) {
        if (memberCardService.updateById(memberCard)) {
            return ResultUtils.success("编辑成功!");
        }
        return ResultUtils.error("编辑失败!");
    }

    /**
     * 删除会员卡信息
     * 
     * <p>根据会员卡ID删除指定的会员卡信息</p>
     * 
     * @param cardId 会员卡ID
     * @return 删除操作结果
     */
    @DeleteMapping("/{cardId}")
    public ResultVo delete(@PathVariable("cardId") Long cardId) {
        if (memberCardService.removeById(cardId)) {
            return ResultUtils.success("删除成功!");
        }
        return ResultUtils.error("删除失败!");
    }

    /**
     * 查询会员卡列表
     * 
     * <p>分页查询会员卡列表，支持按会员卡标题进行模糊搜索</p>
     * 
     * @param listCard 查询参数，包含分页信息和搜索条件
     * @return 会员卡列表查询结果
     */
    @GetMapping("/list")
    public ResultVo list(ListCard listCard) {
        // 构造分页对象
        IPage<MemberCard> page = new Page<>(
                listCard.getCurrentPage(), listCard.getPageSize());
        
        // 构造查询条件
        QueryWrapper<MemberCard> query = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(listCard.getTitle())) {
            query.lambda().like(MemberCard::getTitle, listCard.getTitle());
        }
        
        IPage<MemberCard> list = memberCardService.page(page, query);
        return ResultUtils.success("查询成功", list);
    }
}
