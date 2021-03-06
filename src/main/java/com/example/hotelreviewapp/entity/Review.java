package com.example.hotelreviewapp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private long evaluation;
    private String body;

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




    public long getEvaluation() {
        return evaluation;
    }




    public void setEvaluation(long evaluation) {
        this.evaluation = evaluation;
    }




    public String getBody() {
        return body;
    }




    public void setBody(String body) {
        this.body = body;
    }




    public Hotel getHotel() {
        return hotel;
    }




    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }




    

    

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
    

    

    public Review() {}
}