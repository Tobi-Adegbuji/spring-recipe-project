package com.tobiadegbuji.recipe.services;

import com.tobiadegbuji.recipe.commands.RecipeCommand;
import com.tobiadegbuji.recipe.converters.RecipeCommandToRecipe;
import com.tobiadegbuji.recipe.converters.RecipeToRecipeCommand;
import com.tobiadegbuji.recipe.domain.Recipe;
import com.tobiadegbuji.recipe.exceptions.NotFoundException;
import com.tobiadegbuji.recipe.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j //Helps add logging functionality
public class RecipeServiceImpl implements RecipeService {

   private final RecipeRepository recipeRepository;
   private final RecipeCommandToRecipe recipeCommandToRecipe;
   private final RecipeToRecipeCommand recipeToRecipeCommand;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }

    @Override
    public Set<Recipe> getRecipes() {
        log.debug("Im in the service");
        Set<Recipe> recipeSet = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
        return recipeSet;
    }

    @Override
    public Recipe findById(Long l){
        Optional<Recipe> recipeOptional = recipeRepository.findById(l);

        if(!recipeOptional.isPresent())
        throw new NotFoundException("Cannot find recipe. Specifically for the recipe ID value: " + l);
        else
            return recipeOptional.get();

    }

    @Transactional
    @Override
    public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand) {
        Recipe detachedRecipe = recipeCommandToRecipe.convert(recipeCommand);
        Recipe savedRecipe = recipeRepository.save(detachedRecipe);
        log.debug("Saved Recipe" + savedRecipe.getId());
        return recipeToRecipeCommand.convert(savedRecipe);

    }

    @Transactional
    @Override
    public RecipeCommand findCommandById(long id) {
        return recipeToRecipeCommand.convert(findById(id));
    }


    @Override
    public void deleteById(Long id) {
        recipeRepository.deleteById(id);
    }
}
