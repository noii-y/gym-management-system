package com.noy.web.sys_role.entity;

import lombok.Data;

/**
 * 选择类型实体类
 * 
 * <p>用于封装下拉选择框的选项数据，包括：</p>
 * <ul>
 *   <li>选项值 - 选项的实际值</li>
 *   <li>显示标签 - 选项的显示文本</li>
 *   <li>前端组件 - 用于前端下拉框、单选框等组件</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
@Data
public class SelectType {
    /**
     * 选项值
     */
    private Long value;
    
    /**
     * 显示标签
     */
    private String label;
}
