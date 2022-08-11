package com.ssafy.trippy.Controller;


// import 생략...

import com.ssafy.trippy.Config.JwtProvider;
import com.ssafy.trippy.Dto.ChatDto.ChatMessage;
import com.ssafy.trippy.Repository.ChatRoomRepository;
import com.ssafy.trippy.Service.Impl.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@CrossOrigin("*")
public class ChatController {

    private final JwtProvider jwtTokenProvider;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatService chatService;

    /**
     * websocket "/pub/chat/message"로 들어오는 메시징을 처리한다.
     */
    @MessageMapping("/chat/message")
    public void message(ChatMessage message, @Header("token") String token) {
        String email = jwtTokenProvider.getUserPk(token);
        // 로그인 회원 정보로 대화명 설정
        message.setSender(email);
        // 채팅방 인원수 세팅
        message.setUserCount(chatRoomRepository.getUserCount(message.getRoomId()));
        // Websocket에 발행된 메시지를 redis로 발행(publish)
        chatService.sendChatMessage(message);
    }
}