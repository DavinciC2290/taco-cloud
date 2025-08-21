package com.example.saul.taco_cloud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import jakarta.validation.Valid;
import org.springframework.validation.Errors;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.saul.taco_cloud.domain.TacoOrder;
import com.example.saul.taco_cloud.repositories.TacoOrderRepository;


@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {
    
    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private TacoOrderRepository tacoOrderRepository;


    @GetMapping("/current")
    public String orderForm(){
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid TacoOrder tacoOrder, Errors errors, SessionStatus sessionStatus)
    {

        if(errors.hasErrors())
        {
            return "orderForm";
        }

        tacoOrder.setPlacedAt(new Date());
        tacoOrderRepository.save(tacoOrder);
        log.info("Order submitted: {}", tacoOrder);
        sessionStatus.setComplete();
         
        return "redirect:/";

    }

}
