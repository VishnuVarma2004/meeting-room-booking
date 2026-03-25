package com.vishnu.meetingroombooking.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vishnu.meetingroombooking.entity.Booking;
import com.vishnu.meetingroombooking.entity.BookingStatus;
import com.vishnu.meetingroombooking.entity.Room;
import com.vishnu.meetingroombooking.repository.BookingRepository;
import com.vishnu.meetingroombooking.repository.RoomRepository;

@Service
public class BookingService {
	@Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private RoomRepository roomRepository;
    
    public Booking createBooking(Long roomId, String userName,
            LocalDateTime startTime,
            LocalDateTime endTime) {

// 1️⃣ Get Room
Room room = roomRepository.findById(roomId)
.orElseThrow(() -> new RuntimeException("Room not found"));

// 2️⃣ Check overlapping bookings
List<Booking> overlappingBookings =
bookingRepository.findByRoom_IdAndStartTimeLessThanAndEndTimeGreaterThan(
   roomId, endTime, startTime);

if (!overlappingBookings.isEmpty()) {
throw new RuntimeException("Room already booked for this time!");
}

// 3️⃣ Create Booking
Booking booking = new Booking();
booking.setRoom(room);
booking.setUserName(userName);
booking.setStartTime(startTime);
booking.setEndTime(endTime);
booking.setStatus(BookingStatus.ACTIVE);

// 4️⃣ Save
return bookingRepository.save(booking);
}
}
