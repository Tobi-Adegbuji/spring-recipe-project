package com.tobiadegbuji.recipe.services;

import com.tobiadegbuji.recipe.converters.RecipeCommandToRecipe;
import com.tobiadegbuji.recipe.converters.RecipeToRecipeCommand;
import com.tobiadegbuji.recipe.domain.Recipe;
import com.tobiadegbuji.recipe.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;
    RecipeToRecipeCommand recipeToRecipeCommand;
    RecipeCommandToRecipe recipeCommandToRecipe;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
    }

    @Test
    public void getRecipeByIdTest() throws Exception{
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        Recipe recipeReturned = recipeService.findById(1L);

        assertNotNull(recipeReturned,"Null Recipe Returned");
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();

    }

    @Test
    void getRecipes() {
        //Create recipe to add to set
        Recipe recipe = new Recipe();
        HashSet recipesData = new HashSet();
        recipesData.add(recipe);
        Mockito.lenient().when(recipeRepository.findAll()).thenReturn(recipesData);

        Set<Recipe> recipes = recipeService.getRecipes();
        assertEquals(recipes.size(),1);

        //Verifying findAll is called ONCE
        Mockito.verify(recipeRepository, Mockito.times(1)).findAll();
    }
}