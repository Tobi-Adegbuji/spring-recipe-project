package com.tobiadegbuji.recipe.controllers;

import com.tobiadegbuji.recipe.domain.Recipe;
import com.tobiadegbuji.recipe.exceptions.NotFoundException;
import com.tobiadegbuji.recipe.repositories.RecipeRepository;
import com.tobiadegbuji.recipe.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class RecipeControllerTest {

    RecipeController recipeController;

    @Mock
    RecipeService recipeService;
    @Mock
    RecipeRepository recipeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeController = new RecipeController(recipeService);
    }

    @Test
    public void getRecipe() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();

        when(recipeService.findById(anyLong())).thenReturn(recipe);

        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/show"))
                .andExpect(model().attributeExists("recipe"));
    }

//    @Test(expected = NotFoundException.class)
//    public void getRecipeByIdTestNotFound() throws Exception{
//        Optional<Recipe> recipeOptional = Optional.empty();
//        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
//        //Purposely trying to cause exception
//        Recipe recipeReturned = recipeService.findById(1L);
//
//        assertThrows(NotFoundException.class, ()->{})
//
//    }

}