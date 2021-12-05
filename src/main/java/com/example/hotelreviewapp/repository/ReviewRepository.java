package com.example.hotelreviewapp.repository;

import java.util.List;

import com.example.hotelreviewapp.entity.Review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
     List<Review> findByHotelId(Long hotel_id);
}