package com.example.hotelreviewapp.controller;

import java.util.List;

import com.example.hotelreviewapp.entity.Hotel;
import com.example.hotelreviewapp.entity.Reservation;
import com.example.hotelreviewapp.repository.HotelRepository;
import com.example.hotelreviewapp.repository.ReservationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hotels/{hotel_id}/reservations")
public class ReservationController {
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @GetMapping
    public String index(@PathVariable Long hotel_id, Model model) {
        List<Reservation> reservations = reservationRepository.findByHotelId(hotel_id);
        Hotel hotel = hotelRepository.findById(hotel_id).orElse(null);
        model.addAttribute("reservations", reservations);
        model.addAttribute("hotel", hotel);
        model.addAttribute("title", "Reservation Index");
        return "reservation/index";
    }

    @GetMapping("new")
    public String newReservation(@PathVariable Long hotel_id, Model model) {
        Hotel hotel = hotelRepository.findById(hotel_id).orElse(null);
        model.addAttribute("hotel", hotel);
        model.addAttribute("title", "新しく予約する");
        return "reservation/new";
    }

    @PostMapping
    public String create(@PathVariable Long hotel_id, @ModelAttribute Reservation reservation) {
        Hotel hotel = hotelRepository.findById(hotel_id).orElse(null);
        reservation.setHotel(hotel);
        reservationRepository.save(reservation);
        return "redirect:/hotels/{hotel_id}/reservations/"+reservation.getId();
    }



    @GetMapping("{id}")
    public String show(@PathVariable Long hotel_id,@PathVariable Long id, Model model) {
        Hotel hotel = hotelRepository.findById(hotel_id).orElse(null);
        Reservation reservation = reservationRepository.findById(id).orElse(null);
        model.addAttribute("reservation", reservation);
        model.addAttribute("hotel", hotel);
        model.addAttribute("title", "予約ありがとうございます");
        return "reservation/show";
    }

}