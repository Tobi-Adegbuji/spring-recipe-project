package com.tobiadegbuji.recipe.controllers;

import com.tobiadegbuji.recipe.services.IngredientService;
import com.tobiadegbuji.recipe.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.control.CodeGenerationHint;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@Slf4j
public class IngredientController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
    }

    @GetMapping("recipe/{id}/ingredients")
    public String getIngredientController(@PathVariable String id, Model model){
        model.addAttribute("recipe",recipeService.findCommandById(Long.parseLong(id)));
        return "recipe/ingredients/list";
    }

    @GetMapping("recipe/{recipeId}/ingredients/{id}/show")
    public String showIngredient(@PathVariable String recipeId, @PathVariable String id, Model model){
        model.addAttribute("ingredient", ingredientService.findById(Long.parseLong(id)));
        model.addAttribute("recipe", recipeService.findById(Long.parseLong(recipeId)));
        return "recipe/ingredients/show";
    }

}
