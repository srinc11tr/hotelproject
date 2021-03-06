package com.example.hotelreviewapp.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.cloudinary.utils.ObjectUtils;
import com.example.hotelreviewapp.config.Singleton;
import com.example.hotelreviewapp.entity.Hotel;
import com.example.hotelreviewapp.entity.Photo;
import com.example.hotelreviewapp.entity.PhotoUpload;
import com.example.hotelreviewapp.form.HotelForm;
import com.example.hotelreviewapp.repository.HotelRepository;
import com.example.hotelreviewapp.repository.PhotoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private PhotoRepository photoRepository;

    @GetMapping
    public String index(Model model, @RequestParam(defaultValue = "") String name) {
        List<Hotel> hotels = hotelRepository.findByNameContaining(name);
       model.addAttribute("queryName", name);
        model.addAttribute("hotels", hotels);
        model.addAttribute("title", "Hotel Index");
        return "hotel/index";
    }
    @GetMapping("new")
    public String newHotel(Model model) {
        model.addAttribute("title", "New Hotel");
        return "hotel/new";
    }

    @PostMapping
    public String create(@ModelAttribute HotelForm hotelForm) throws IOException {
        Hotel hotel = new Hotel();
        hotel.setName(hotelForm.getName());
        hotel.setDescription(hotelForm.getDescription());
        PhotoUpload photoUpload = new PhotoUpload();
        Map<?, ?> uploadResult = null;
        if (hotelForm.getFile() != null && !hotelForm.getFile().isEmpty()) {
            uploadResult = Singleton.getCloudinary().uploader().upload(hotelForm.getFile().getBytes(),
                    ObjectUtils.asMap("resource_type", "auto"));
            photoUpload.setPublicId((String) uploadResult.get("public_id"));
            Object version = uploadResult.get("version");
            if (version instanceof Integer) {
                photoUpload.setVersion(Long.valueOf((Integer) version));
            } else {
                photoUpload.setVersion((Long) version);
            }

            photoUpload.setSignature((String) uploadResult.get("signature"));
            photoUpload.setFormat((String) uploadResult.get("format"));
            photoUpload.setResourceType((String) uploadResult.get("resource_type"));
        }

        Photo photo = new Photo();
        photo.setIdea(hotel);
        photo.setUpload(photoUpload);

        hotelRepository.save(hotel);
        photoRepository.save(photo);
        return "redirect:/hotels";
    }

    @GetMapping("{id}")
    public String show(@PathVariable Long id, Model model) {
        Hotel hotel = hotelRepository.findById(id).orElse(null);
        model.addAttribute("hotel", hotel);
        model.addAttribute("title", "Show Hotel");
        return "hotel/show";
    }

}