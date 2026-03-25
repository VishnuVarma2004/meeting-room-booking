package com.vishnu.meetingroombooking.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vishnu.meetingroombooking.entity.Booking;

	public interface BookingRepository extends JpaRepository<Booking, Long> {

	    // 🔥 Find overlapping bookings for a room
		List<Booking> findByRoom_IdAndStartTimeLessThanAndEndTimeGreaterThan(
		        Long roomId,
		        LocalDateTime endTime,
		        LocalDateTime startTime
		);
	}
