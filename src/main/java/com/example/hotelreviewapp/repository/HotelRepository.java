package com.example.hotelreviewapp.repository;

import java.util.List;

import com.example.hotelreviewapp.entity.Hotel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

    List<Hotel> findByNameContaining(String name);
}