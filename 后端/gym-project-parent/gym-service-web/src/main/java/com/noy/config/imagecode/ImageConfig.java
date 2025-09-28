package com.noy.config.imagecode;

// Java 标准库
import java.util.Properties;

// 第三方库 - Kaptcha验证码
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

// Spring 框架相关
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 图片验证码配置类
 * 
 * <p>配置Kaptcha图片验证码生成器的各项参数，用于系统登录安全验证</p>
 * 
 * <p>主要配置包括：</p>
 * <ul>
 *   <li>验证码外观 - 边框、颜色、字体等视觉效果</li>
 *   <li>验证码内容 - 字符集、长度、大小等内容设置</li>
 *   <li>安全特性 - 干扰线、图片效果等安全增强</li>
 *   <li>尺寸规格 - 图片宽度、高度等尺寸参数</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
@Configuration
public class ImageConfig {

    /**
     * 创建默认的Kaptcha验证码生成器
     * 
     * <p>配置验证码的各项参数，包括外观、内容和安全特性</p>
     * 
     * @return DefaultKaptcha 配置完成的验证码生成器实例
     */
    @Bean
    public DefaultKaptcha getDefaultKaptcha() {
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        
        // ==================== 外观配置 ====================
        
        // 验证码是否有边框
        properties.setProperty(Constants.KAPTCHA_BORDER, "yes");
        // 边框颜色 (RGB: 105,179,90 - 绿色)
        properties.setProperty(Constants.KAPTCHA_BORDER_COLOR, "105,179,90");
        // 字体颜色
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_COLOR, "blue");
        
        // ==================== 尺寸配置 ====================
        
        // 验证码图片宽度
        properties.setProperty(Constants.KAPTCHA_IMAGE_WIDTH, "200");
        // 验证码图片高度
        properties.setProperty(Constants.KAPTCHA_IMAGE_HEIGHT, "36");
        // 字体大小
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_SIZE, "34");
        
        // ==================== 内容配置 ====================
        
        // 生成验证码的字符集 (仅数字0-9)
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_CHAR_STRING, "0123456789");
        // 验证码位数
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_CHAR_LENGTH, "4");
        // 字体样式
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_NAMES, "楷体");
        
        // ==================== 安全配置 ====================
        
        // 去掉干扰线 (提高可读性)
        properties.setProperty(Constants.KAPTCHA_NOISE_IMPL, "com.google.code.kaptcha.impl.NoNoise");
        // 图片效果 (阴影效果增强安全性)
        properties.setProperty(Constants.KAPTCHA_OBSCURIFICATOR_IMPL, "com.google.code.kaptcha.impl.ShadowGimpy");
        
        // 应用配置
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}
