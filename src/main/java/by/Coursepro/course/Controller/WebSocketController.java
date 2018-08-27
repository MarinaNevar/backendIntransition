package by.Coursepro.course.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

//@Controller
//@EnableWebSocketMessageBroker
//@EnableScheduling
//@RequiredArgsConstructor
//public class WebSocketController implements WebSocketMessageBrokerConfigurer {
//
//    private final SimpMessagingTemplate template;
//
//
//    @MessageMapping("/send/message")
//    public void onRecievedMessage(String comment) {
//        this.template.convertAndSend("/comment", comment);
//    }
//}