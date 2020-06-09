package com.tobiadegbuji.recipe.controllers;

import com.tobiadegbuji.recipe.repositories.CategoryRepository;
import com.tobiadegbuji.recipe.repositories.RecipeRepository;
import com.tobiadegbuji.recipe.repositories.UnitOfMeasureRepository;
import com.tobiadegbuji.recipe.services.RecipeService;
import com.tobiadegbuji.recipe.services.RecipeServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model) {

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