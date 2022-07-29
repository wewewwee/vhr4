package com.qfedu.vhr.admin.controller;

import com.qfedu.vhr.framework.entity.Chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class ChatController {

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    /**
     *
     * @param chat
     * @param principal 这个就是当前登录用户对象
     */
    @MessageMapping("/myws/chat")
    public void chat(Chat chat, Principal principal) {
        chat.setFrom(principal.getName());
        //发送一条消息
        simpMessagingTemplate.convertAndSendToUser(chat.getTo(), "/queue/chat", chat);
    }
}
