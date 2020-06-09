package com.tobiadegbuji.recipe.services;

import com.tobiadegbuji.recipe.domain.Recipe;
import org.springframework.stereotype.Service;

import java.util.Set;


public interface RecipeService{

    Set<Recipe> getRecipes();

}