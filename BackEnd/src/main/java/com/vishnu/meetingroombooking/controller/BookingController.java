package com.vishnu.meetingroombooking.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vishnu.meetingroombooking.entity.Booking;
import com.vishnu.meetingroombooking.service.BookingService;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "*")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    // 🔥 Create Booking API
    @PostMapping
    public Booking createBooking(
            @RequestParam Long roomId,
            @RequestParam String userName,
            @RequestParam String startTime,
            @RequestParam String endTime) {

        try {
            LocalDateTime start = LocalDateTime.parse(startTime);
            LocalDateTime end = LocalDateTime.parse(endTime);

            return bookingService.createBooking(roomId, userName, start, end);

        } catch (RuntimeException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
}