package com.noy.web.member.entity;

import lombok.Data;

/**
 * 会员加入参数类
 * 
 * <p>用于封装会员加入会员卡的请求参数，包括：</p>
 * <ul>
 *   <li>会员标识 - 要加入的会员ID</li>
 *   <li>会员卡标识 - 要加入的会员卡ID</li>
 *   <li>支持会员卡关联操作</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
@Data
public class JoinParam {

    /**
     * 会员ID
     */
    private Long memberId;
    
    /**
     * 会员卡ID
     */
    private Long cardId;
}
