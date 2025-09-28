package com.noy.web.home.entity;

import lombok.Data;

/**
 * 图表数据项实体类
 * 
 * <p>用于封装ECharts图表的数据项信息，包括：</p>
 * <ul>
 *   <li>名称标识 - 数据项的名称标签</li>
 *   <li>数值信息 - 数据项的具体数值</li>
 *   <li>图表展示 - 用于各类统计图表的数据展示</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
@Data
public class EchartItem {

    /**
     * 数据项名称
     */
    private String name;
    
    /**
     * 数据项数值
     */
    private Integer value;
}
