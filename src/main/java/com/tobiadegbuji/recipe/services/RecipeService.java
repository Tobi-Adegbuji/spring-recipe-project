package com.tobiadegbuji.recipe.services;

import com.tobiadegbuji.recipe.commands.RecipeCommand;
import com.tobiadegbuji.recipe.domain.Recipe;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;


public interface RecipeService{

    Set<Recipe> getRecipes();

    Recipe findById(Long l);

    RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);

    RecipeCommand findCommandById(long id);

    void deleteById(Long id);



}