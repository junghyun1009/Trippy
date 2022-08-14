package com.ssafy.trippy.Repository;

import com.ssafy.trippy.Dto.ChatDto.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, String> {

}