package com.tobiadegbuji.recipe.services;

import com.tobiadegbuji.recipe.commands.IngredientCommand;
import com.tobiadegbuji.recipe.domain.Ingredient;

public interface IngredientService {
    IngredientCommand findById(Long id);
}
