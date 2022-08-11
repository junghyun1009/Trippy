package com.ssafy.trippy.Dto.ChatDto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChatMessage {
    public enum MessageType {
        ENTER, TALK, QUIT;
    }
    private MessageType type;
    private String roomId;
    private String sender;
    private String message;
    private Long userCount;

    @Builder
    public ChatMessage(MessageType type, String roomId, String sender, String message, Long userCount) {
        this.type = type;
        this.roomId = roomId;
        this.sender = sender;
        this.message = message;
        this.userCount = userCount;
    }
}
