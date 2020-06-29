package com.tobiadegbuji.recipe.services;

import com.tobiadegbuji.recipe.commands.IngredientCommand;
import com.tobiadegbuji.recipe.converters.IngredientCommandToIngredient;
import com.tobiadegbuji.recipe.converters.IngredientToIngredientCommand;
import com.tobiadegbuji.recipe.domain.Ingredient;
import com.tobiadegbuji.recipe.repositories.IngredientRepository;
import com.tobiadegbuji.recipe.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
public class IngredientServiceImpl implements IngredientService {

    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;

    public IngredientServiceImpl(RecipeRepository recipeRepository, IngredientRepository ingredientRepository,
                                 IngredientToIngredientCommand ingredientToIngredientCommand, IngredientCommandToIngredient ingredientCommandToIngredient) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
    }

    @Override
    public IngredientCommand findById(Long id) {
        Optional<Ingredient> ingredientOptional = ingredientRepository.findById(id);

        if(ingredientOptional.isPresent())
        return ingredientToIngredientCommand.convert(ingredientOptional.get());

        else
            throw new RuntimeException("Ingredient Not Found");
    }


    @Transactional
    @Override
    public IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand) {
        Ingredient detachedIngredient = ingredientCommandToIngredient.convert(ingredientCommand);
        if(detachedIngredient == null)
            throw new RuntimeException("Cannot save ingredient");
        Ingredient savedIngredient = ingredientRepository.save(detachedIngredient);
        return ingredientToIngredientCommand.convert(savedIngredient);
    }


    @Override
    public void deleteById(Long id) {
        ingredientRepository.deleteById(id);
    }
}
