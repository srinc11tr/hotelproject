package com.example.hotelreviewapp.repository;

import com.example.hotelreviewapp.entity.Photo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {

}