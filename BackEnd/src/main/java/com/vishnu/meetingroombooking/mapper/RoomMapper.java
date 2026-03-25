package com.vishnu.meetingroombooking.mapper;

import com.vishnu.meetingroombooking.dto.RoomRequestDto;
import com.vishnu.meetingroombooking.dto.RoomResponseDto;
import com.vishnu.meetingroombooking.entity.Room;

public class RoomMapper {
	 public static Room toEntity(RoomRequestDto dto) {
	        Room room = new Room();
	        room.setName(dto.getName());
	        room.setCapacity(dto.getCapacity());
	        room.setLocation(dto.getLocation());
	        return room;
	    }

	    // 🔹 Convert Entity → ResponseDto
	    public static RoomResponseDto toResponseDto(Room room) {
	        RoomResponseDto dto = new RoomResponseDto();
	        dto.setId(room.getId());
	        dto.setName(room.getName());
	        dto.setCapacity(room.getCapacity());
	        dto.setLocation(room.getLocation());
	        return dto;
	    }
}
