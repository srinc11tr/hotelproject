package com.example.hotelreviewapp.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String email;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date finishDate;
    private long numberOfPeople;


    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;


    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public Date getStartDate() {
        return startDate;
    }


    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }


    public Date getFinishDate() {
        return finishDate;
    }


    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }


    public long getNumberOfPeople() {
        return numberOfPeople;
    }


    public void setNumberOfPeople(long numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }


    public Hotel getHotel() {
        return hotel;
    }


    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

}