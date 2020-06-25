package com.tobiadegbuji.recipe.services;

import com.tobiadegbuji.recipe.domain.Recipe;
import com.tobiadegbuji.recipe.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j //Helps add logging functionality
public class RecipeServiceImpl implements RecipeService {

   private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<Recipe> getRecipes() {
        log.debug("Im in the service");
        Set<Recipe> recipeSet = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
        return recipeSet;
    }

    @Override
    public Recipe findById(Long l) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(l);

        if(recipeOptional.isPresent())
            return recipeOptional.get();
        else
            throw  new RuntimeException("Recipe Not Found");
    }
}
