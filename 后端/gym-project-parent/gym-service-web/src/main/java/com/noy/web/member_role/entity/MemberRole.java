package com.noy.web.member_role.entity;

// MyBatis Plus 相关
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

// Lombok 注解
import lombok.Data;

/**
 * 会员角色关联实体类
 * 
 * <p>对应数据库中的member_role表，用于管理会员与角色的关联关系</p>
 * 
 * <p>实现会员权限管理的多对多关系映射</p>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
@Data
@TableName("member_role")
public class MemberRole {

    /**
     * 会员角色关联ID（主键，自增）
     */
    @TableId(type = IdType.AUTO)
    private Long memberRoleId;
    
    /**
     * 会员ID
     */
    private Long memberId;
    
    /**
     * 角色ID
     */
    private Long roleId;
}
