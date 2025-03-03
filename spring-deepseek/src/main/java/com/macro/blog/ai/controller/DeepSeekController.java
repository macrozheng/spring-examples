package com.macro.blog.ai.controller;

import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.Map;

/**
 * @auther macrozheng
 * @description 对接DeepSeek后生成回答的Controller
 * @date 2025/2/21
 * @github https://github.com/macrozheng
 */
@RestController
public class DeepSeekController {

    private final OpenAiChatModel chatModel;

    @Autowired
    public DeepSeekController(OpenAiChatModel chatModel) {
        this.chatModel = chatModel;
    }

    /**
     * 根据消息直接输出回答
     */
    @GetMapping("/ai/chat")
    public Map chat(@RequestParam(value = "message") String message) {
        return Map.of("generation", this.chatModel.call(message));
    }

    /**
     * 根据消息采用流式输出，输出回答
     */
    @GetMapping(value = "/ai/chatFlux", produces = MediaType.TEXT_EVENT_STREAM_VALUE + "; charset=UTF-8")
    public Flux<ChatResponse> chatFlux(@RequestParam(value = "message") String message) {
        Prompt prompt = new Prompt(new UserMessage(message));
        return this.chatModel.stream(prompt);
    }

}
