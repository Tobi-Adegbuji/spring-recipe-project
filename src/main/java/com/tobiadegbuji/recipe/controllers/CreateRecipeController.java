package com.tobiadegbuji.recipe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CreateRecipeController {

    @RequestMapping("/recipe/recipeform")
    public String getEmptyPage(){
        return "recipe/recipeform";
    }

}
