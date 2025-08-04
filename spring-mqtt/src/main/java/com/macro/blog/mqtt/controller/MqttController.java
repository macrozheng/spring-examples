package com.macro.blog.mqtt.controller;


import com.macro.blog.mqtt.common.api.CommonResult;
import com.macro.blog.mqtt.component.MqttGateway;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther macrozheng
 * @description MQTT测试接口
 * @date 2025/8/1
 * @github https://github.com/macrozheng
 */
@Slf4j
@RestController
@Tag(name = "MqttController", description = "MQTT测试接口")
@RequestMapping("/mqtt")
public class MqttController {

    @Autowired
    private MqttGateway mqttGateway;

    @PostMapping("/sendToDefaultTopic")
    @Operation(summary = "向默认主题发送消息")
    public CommonResult sendToDefaultTopic(String payload) {
        mqttGateway.sendToMqtt(payload);
        return CommonResult.success(null);
    }

    @PostMapping("/sendToTopic")
    @Operation(summary = "向指定主题发送消息")
    public CommonResult sendToTopic(String payload, String topic) {
        mqttGateway.sendToMqtt(payload, topic);
        return CommonResult.success(null);
    }
}
