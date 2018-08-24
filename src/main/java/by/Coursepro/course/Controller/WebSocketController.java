package by.Coursepro.course.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

//@Controller
//@RequiredArgsConstructor
//public class WebSocketController {
//
//    private final SimpMessagingTemplate template;
//
//
//    @MessageMapping("/send/message")
//    public void onRecievedMessage(String comment) {
//        this.template.convertAndSend("/comment", comment);
//    }
//}