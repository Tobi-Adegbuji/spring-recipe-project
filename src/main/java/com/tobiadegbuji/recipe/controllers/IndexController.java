package com.tobiadegbuji.recipe.controllers;

import com.tobiadegbuji.recipe.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model) {
        log.debug("Getting index page");
        model.addAttribute("recipes", recipeService.getRecipes());

        return "index";
    }
}


//    @RequestMapping({"", "/", "/index"})
//    public String getIndexPage(){
//
//        //For Testing
////        categoryRepository.findByCategoryName("Mexican")
////                .ifPresent(category -> System.out.println("Category id: " + category.getId()));
////        unitOfMeasureRepository.findByUnitOfMeasure("Teaspoon")
////                .ifPresent(unitOfMeasure -> System.out.println("Unit of measure: " + unitOfMeasure.getId()));
//
//        return "index";
//    }