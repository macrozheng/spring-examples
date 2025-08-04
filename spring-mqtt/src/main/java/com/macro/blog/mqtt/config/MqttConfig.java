package com.macro.blog.mqtt.config;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @auther macrozheng
 * @description MQTT相关配置
 * @date 2025/8/1
 * @github https://github.com/macrozheng
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Component
@ConfigurationProperties(prefix = "rabbitmq.mqtt")
public class MqttConfig {
    /**
     * RabbitMQ连接用户名
     */
    private String username;
    /**
     * RabbitMQ连接密码
     */
    private String password;
    /**
     * RabbitMQ的MQTT默认topic
     */
    private String defaultTopic;
    /**
     * RabbitMQ的MQTT连接地址
     */
    private String url;
}
