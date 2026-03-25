package com.vishnu.meetingroombooking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vishnu.meetingroombooking.dto.RoomRequestDto;
import com.vishnu.meetingroombooking.dto.RoomResponseDto;
import com.vishnu.meetingroombooking.service.RoomService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/rooms")
public class RoomController {
	@Autowired
    private RoomService roomService;

    // 🔹 CREATE
    @PostMapping
    public ResponseEntity<RoomResponseDto> createRoom(@Valid @RequestBody RoomRequestDto dto) {
        return ResponseEntity.ok(roomService.createRoom(dto));
    }

    // 🔹 GET ALL
    @GetMapping
    public List<RoomResponseDto> getAllRooms() {
        return roomService.getAllRooms();
    }

    // 🔹 GET BY ID
    @GetMapping("/{id}")
    public RoomResponseDto getRoomById(@PathVariable Long id) {
        return roomService.getRoomById(id);
    }

    // 🔹 UPDATE
    @PutMapping("/{id}")
    public RoomResponseDto updateRoom(@PathVariable Long id,
                                      @Valid @RequestBody RoomRequestDto dto) {
        return roomService.updateRoom(id, dto);
    }

    // 🔹 DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
        return ResponseEntity.ok("Room deleted successfully");
    }
}
