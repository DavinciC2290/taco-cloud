package com.example.saul.taco_cloud.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.validation.Valid;
import com.example.saul.taco_cloud.web.RegistrationFormDto;
import com.example.saul.taco_cloud.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;


@RequestMapping("/register")
@Controller
public class RegistrationController {

    @Autowired
    private  UserRepository userRepository;

    @Autowired
    private  PasswordEncoder passwordEncoder;

    @GetMapping
    public String registerForm(Model model)
    {
        model.addAttribute("formObject", new RegistrationFormDto());
        return "register";
    }

    @PostMapping
    public String processRegistration(@Valid @ModelAttribute("formObject") RegistrationFormDto formObject, Errors errors)
    {
        if(errors.hasErrors())
        {
            return "register";
        }

        userRepository.save(formObject.toUser(passwordEncoder));
        return "redirect:/login";
    }
    
}
