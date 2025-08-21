package com.example.saul.taco_cloud.controllers;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import jakarta.validation.Valid;
import org.springframework.validation.Errors;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.saul.taco_cloud.domain.Ingredient;
import com.example.saul.taco_cloud.domain.Ingredient.Type;
import com.example.saul.taco_cloud.domain.Taco;
import com.example.saul.taco_cloud.domain.TacoOrder;
import com.example.saul.taco_cloud.repositories.IngredientRepository;


@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {

     private static final Logger log = LoggerFactory.getLogger(DesignTacoController.class);

     private IngredientRepository ingredientRepository;


     public DesignTacoController(IngredientRepository ingredientRepository)
     {
        this.ingredientRepository = ingredientRepository;
     }


    @ModelAttribute
    public void addIngredientsToModel(Model model)
    {
        List<Ingredient> ingredients = ingredientRepository.findAll();

        Type[] types = Ingredient.Type.values();
        for(Type type : types)
        {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }

    }


    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order()
    {
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco()
    {
        return new Taco();
    }

    
    @GetMapping
    public String showDesignForm()
    {
        return "design";
    }


     //The annotation @ModelAttribute in the parameter tacoOrder is fetching the object of the session
     @PostMapping
     public String processTaco(@Valid Taco taco, Errors errors,  @ModelAttribute TacoOrder tacoOrder)
     {

        if(errors.hasErrors())
        {
            return "design";
        }

        taco.setCreatedAt(new Date());
        taco.setTacoOrder(tacoOrder);
        tacoOrder.addTaco(taco);
        log.info("Processing taco: {}", taco.toString());

        return "redirect:/orders/current";

     }



    private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Type type)
    {
        return ingredients
                .stream()
                .filter(ing -> ing.getType().equals(type))
                .collect(Collectors.toList());
        
    }



    
}
