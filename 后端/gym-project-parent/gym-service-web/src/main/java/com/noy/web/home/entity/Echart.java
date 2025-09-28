package com.noy.web.home.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * ECharts图表数据实体类
 * 
 * <p>用于封装ECharts图表所需的数据结构，包括：</p>
 * <ul>
 *   <li>名称列表 - 图表的X轴标签或分类名称</li>
 *   <li>数值列表 - 对应的数据值</li>
 *   <li>支持各种图表类型（柱状图、折线图、饼图等）</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
@Data
public class Echart {

    /**
     * 名称列表
     * <p>存储图表的分类名称或X轴标签</p>
     */
    private List<String> names = new ArrayList<>();
    
    /**
     * 数值列表
     * <p>存储对应名称的数据值</p>
     */
    private List<Integer> values = new ArrayList<>();
}
