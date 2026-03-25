package com.vishnu.meetingroombooking.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vishnu.meetingroombooking.dto.RoomRequestDto;
import com.vishnu.meetingroombooking.dto.RoomResponseDto;
import com.vishnu.meetingroombooking.entity.Room;
import com.vishnu.meetingroombooking.exception.RoomNotFoundException;
import com.vishnu.meetingroombooking.mapper.RoomMapper;
import com.vishnu.meetingroombooking.repository.RoomRepository;

@Service
public class RoomServiceImpl implements RoomService{

	@Autowired
    private RoomRepository roomRepository;

    // 🔹 CREATE
    @Override
    public RoomResponseDto createRoom(RoomRequestDto dto) {
        Room room = RoomMapper.toEntity(dto);
        Room savedRoom = roomRepository.save(room);
        return RoomMapper.toResponseDto(savedRoom);
    }

    // 🔹 GET ALL
    @Override
    public List<RoomResponseDto> getAllRooms() {
        return roomRepository.findAll()
                .stream()
                .map(RoomMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    // 🔹 GET BY ID
    @Override
    public RoomResponseDto getRoomById(Long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RoomNotFoundException("Room not found with id: " + id));

        return RoomMapper.toResponseDto(room);
    }

    // 🔹 UPDATE
    @Override
    public RoomResponseDto updateRoom(Long id, RoomRequestDto dto) {
        Room existingRoom = roomRepository.findById(id)
                .orElseThrow(() -> new RoomNotFoundException("Room not found with id: " + id));

        existingRoom.setName(dto.getName());
        existingRoom.setCapacity(dto.getCapacity());
        existingRoom.setLocation(dto.getLocation());

        Room updatedRoom = roomRepository.save(existingRoom);

        return RoomMapper.toResponseDto(updatedRoom);
    }

    // 🔹 DELETE
    @Override
    public void deleteRoom(Long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RoomNotFoundException("Room not found with id: " + id));

        roomRepository.delete(room);
    }
}
