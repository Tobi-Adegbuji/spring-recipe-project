package com.tobiadegbuji.recipe.controllers;

import com.tobiadegbuji.recipe.domain.Category;
import com.tobiadegbuji.recipe.domain.UnitOfMeasure;
import com.tobiadegbuji.recipe.repositories.CategoryRepository;
import com.tobiadegbuji.recipe.repositories.UnitOfMeasureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

private CategoryRepository categoryRepository;
private UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(){
        categoryRepository.findByCategoryName("Mexican")
                .ifPresent(category -> System.out.println("Category id: " + category.getId()));
        unitOfMeasureRepository.findByUnitOfMeasure("Teaspoon")
                .ifPresent(unitOfMeasure -> System.out.println("Unit of measure: " + unitOfMeasure.getId()));

        return "index";
    }
}
