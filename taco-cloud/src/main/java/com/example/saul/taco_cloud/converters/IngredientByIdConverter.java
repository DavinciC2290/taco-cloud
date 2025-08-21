package com.example.saul.taco_cloud.converters;


import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import  com.example.saul.taco_cloud.domain.Ingredient;
import com.example.saul.taco_cloud.repositories.IngredientRepository;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

    private IngredientRepository ingredientRepository;

    public IngredientByIdConverter(IngredientRepository ingredientRepository)
    {
        this.ingredientRepository = ingredientRepository;
    }


    

    @Override
    public Ingredient convert(String id)
    { 
        return ingredientRepository.findById(id).orElse(null);
    }
    
}
