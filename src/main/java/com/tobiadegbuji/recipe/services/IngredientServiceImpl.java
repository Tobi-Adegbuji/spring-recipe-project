package com.tobiadegbuji.recipe.services;

import com.tobiadegbuji.recipe.commands.IngredientCommand;
import com.tobiadegbuji.recipe.converters.IngredientToIngredientCommand;
import com.tobiadegbuji.recipe.domain.Ingredient;
import com.tobiadegbuji.recipe.domain.Recipe;
import com.tobiadegbuji.recipe.repositories.IngredientRepository;
import com.tobiadegbuji.recipe.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;

    public IngredientServiceImpl(IngredientRepository ingredientRepository, IngredientToIngredientCommand ingredientToIngredientCommand) {
        this.ingredientRepository = ingredientRepository;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
    }

    @Override
    public IngredientCommand findById(Long id) {
        Optional<Ingredient> ingredientOptional = ingredientRepository.findById(id);

        if(ingredientOptional.isPresent())
        return ingredientToIngredientCommand.convert(ingredientOptional.get());

        else
            throw new RuntimeException("Ingredient Not Found");
    }
}
