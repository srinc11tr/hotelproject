package com.example.hotelreviewapp.controller;

import com.example.hotelreviewapp.entity.Contact;
import com.example.hotelreviewapp.repository.ContactRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

   
    @GetMapping("new")
    public String newContact(Model model) {
        model.addAttribute("title", "New Contact");
        return "contact/new";
    }

    @PostMapping
    public String create(@ModelAttribute Contact contact) {
        contactRepository.save(contact);
        return "redirect:/contacts/" + contact.getId();
    }

    

    

    @GetMapping("{id}")
    public String show(@PathVariable Long id, Model model) {
        Contact contact = contactRepository.findById(id).orElse(null);
        model.addAttribute("contact", contact);
        model.addAttribute("title", "Show Contact");
        return "contact/show";
    }

    
}