package com.example.hotelreviewapp.repository;

import java.util.List;

import com.example.hotelreviewapp.entity.Reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
     List<Reservation> findByHotelId(Long hotel_id);
}