package com.tobiadegbuji.recipe.controllers;

import com.tobiadegbuji.recipe.commands.RecipeCommand;
import com.tobiadegbuji.recipe.exceptions.NotFoundException;
import com.tobiadegbuji.recipe.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.id.AbstractPostInsertGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;


@Controller
@Slf4j
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    final static String RECIPE_RECIPEFORM_URL = "recipe/recipeform";

    @GetMapping({"/recipe/allrecipes"})
    public String getIndexPage(Model model) {
        model.addAttribute("recipes", recipeService.getRecipes());
        return "recipe/allrecipes";
    }


    @GetMapping("/recipe/{id}/show")
    public String showById(@PathVariable String id,  Model model){
        model.addAttribute("recipe", recipeService.findById(Long.valueOf(id)));
        return "recipe/show";
    }

    @GetMapping("/recipe/new")
    public String newRecipe(Model model){
        model.addAttribute("recipe", new RecipeCommand());
        return "recipe/newrecipeform";
    }

    @GetMapping("recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
        return RECIPE_RECIPEFORM_URL;
    }

    @PostMapping("recipe_edit")
    public String saveOrUpdateEdit(@Valid @ModelAttribute("recipe") RecipeCommand recipeCommand, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(error -> log.debug(error.toString())); //
            return RECIPE_RECIPEFORM_URL;
        }
        RecipeCommand savedRecipe = recipeService.saveRecipeCommand(recipeCommand);
        return "redirect:/recipe/" + savedRecipe.getId() + "/show";
    }

    @PostMapping("recipeform_new")
    public String saveOrUpdateNew(@Valid @ModelAttribute("recipe") RecipeCommand recipeCommand, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(error -> log.debug(error.toString()));
//           List errorList = bindingResult.getAllErrors();
//           if(!(errorList.size() <= 1))
            return "recipe/newrecipeform";
        }
        RecipeCommand savedRecipe = recipeService.saveRecipeCommand(recipeCommand);
        model.addAttribute("recipe", savedRecipe);
        return "recipe/ingredients/list";
    }

    @GetMapping("recipe/{id}/delete")
    public String deleteById(@PathVariable String id){
        log.debug("Deleting id by: " + id);
        recipeService.deleteById(Long.valueOf(id));
        return "redirect:/recipe/allrecipes";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Exception exception){
        log.error("handling not found exception");
        log.error(exception.getMessage());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("404error");
        modelAndView.addObject("exception", exception);
        return modelAndView;
    }





}
