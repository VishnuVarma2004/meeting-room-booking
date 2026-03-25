package com.vishnu.meetingroombooking.service;

import java.util.List;

import com.vishnu.meetingroombooking.dto.RoomRequestDto;
import com.vishnu.meetingroombooking.dto.RoomResponseDto;

public interface RoomService {
	RoomResponseDto createRoom(RoomRequestDto dto);

    List<RoomResponseDto> getAllRooms();

    RoomResponseDto getRoomById(Long id);

    RoomResponseDto updateRoom(Long id, RoomRequestDto dto);

    void deleteRoom(Long id);
}
