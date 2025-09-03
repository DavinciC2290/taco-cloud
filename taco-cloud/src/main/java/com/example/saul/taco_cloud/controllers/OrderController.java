package com.example.saul.taco_cloud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import jakarta.validation.Valid;
import org.springframework.validation.Errors;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Date;
import com.example.saul.taco_cloud.repositories.UserRepository;
import com.example.saul.taco_cloud.domain.TacoOrder;
import com.example.saul.taco_cloud.repositories.TacoOrderRepository;


@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {
    

    @Autowired
    private TacoOrderRepository tacoOrderRepository;

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/current")
    public String orderForm(
        @ModelAttribute("tacoOrder") TacoOrder tacoOrder, 
        @AuthenticationPrincipal UserDetails principal
        )
    {
        
        userRepository.findByUsername(principal.getUsername()).ifPresent(u -> {
            if(tacoOrder.getDeliveryName() == null || tacoOrder.getDeliveryName().isBlank())
            {
                tacoOrder.setDeliveryName(u.getFullname());
                tacoOrder.setContactPhone(u.getPhone());
                tacoOrder.setDeliveryStreet(u.getStreet());
                tacoOrder.setDeliveryCity(u.getCity());
                tacoOrder.setDeliveryState(u.getState());
                tacoOrder.setDeliveryZip(u.getZip());

            }

        });


        return "orderForm";
    }


    @PostMapping
    public String processOrder(
        @Valid @ModelAttribute("tacoOrder") TacoOrder tacoOrder, 
        Errors errors, 
        @AuthenticationPrincipal UserDetails principal,
        SessionStatus sessionStatus
        )
    {

        if(errors.hasErrors())
        {
            return "orderForm";
        }

        userRepository.findByUsername(principal.getUsername()).ifPresent(u -> {
            tacoOrder.setUser(u);
        });

        tacoOrder.setPlacedAt(new Date());
        tacoOrderRepository.save(tacoOrder);
        sessionStatus.setComplete();
         
        return "orderCompleted";

    }

}
