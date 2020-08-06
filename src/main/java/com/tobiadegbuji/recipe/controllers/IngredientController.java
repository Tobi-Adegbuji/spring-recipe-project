package com.tobiadegbuji.recipe.controllers;

import com.tobiadegbuji.recipe.commands.IngredientCommand;
import com.tobiadegbuji.recipe.converters.UnitOfMeasureCommandToUnitOfMeasure;
import com.tobiadegbuji.recipe.services.IngredientService;
import com.tobiadegbuji.recipe.services.RecipeService;
import com.tobiadegbuji.recipe.services.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class IngredientController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final UnitOfMeasureService unitOfMeasureService;


    public IngredientController(RecipeService recipeService, IngredientService ingredientService, UnitOfMeasureService unitOfMeasureService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @GetMapping("recipe/{id}/ingredients")
    public String getIngredientsPage(@PathVariable String id, Model model){
        model.addAttribute("recipe",recipeService.findCommandById(Long.parseLong(id)));
        return "recipe/ingredients/list";
    }

    @GetMapping("recipe/{recipeId}/ingredients/{id}/show")
    public String showIngredient(@PathVariable String recipeId, @PathVariable String id, Model model){
        model.addAttribute("ingredient", ingredientService.findById(Long.parseLong(id)));
        model.addAttribute("recipe", recipeService.findById(Long.parseLong(recipeId)));
        return "recipe/ingredients/show";
    }

    @GetMapping("recipe/{recipeId}/ingredients/new")
    public String newIngredient(@PathVariable String recipeId, Model model){
        model.addAttribute("recipe", recipeService.findById(Long.parseLong(recipeId)));
        model.addAttribute("uomList",unitOfMeasureService.getUoms());
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setUnitOfMeasure(unitOfMeasureService.findFirstUom());
        model.addAttribute("ingredient", ingredientCommand);
        return "recipe/ingredients/ingredientform";
    }

    @GetMapping("recipe/{recipeId}/ingredients/{id}/update")
    public String updateIngredient(@PathVariable String recipeId, @PathVariable String id, Model model ){
        model.addAttribute("ingredient", ingredientService.findById(Long.parseLong(id)));
        model.addAttribute("recipe", recipeService.findById(Long.parseLong(recipeId)));
        model.addAttribute("uomList",unitOfMeasureService.getUoms());
        return "recipe/ingredients/ingredientform";
    }


    @GetMapping("recipe/{recipeId}/ingredients/{id}/delete")
    public String deleteById(@PathVariable String id, @PathVariable String recipeId){
        ingredientService.deleteById(Long.parseLong(id));
        return "redirect:/recipe/" + recipeId + "/ingredients";
    }

    @PostMapping("recipe/{recipeId}/ingredients")
    public String saveOrUpdate(@ModelAttribute IngredientCommand ingredientCommand){
        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(ingredientCommand);
        log.debug("saved ingredient id:" + savedCommand.getId());
        return "redirect:/recipe/" + savedCommand.getRecipeId() + "/ingredients";
    }

}
