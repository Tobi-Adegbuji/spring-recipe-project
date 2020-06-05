package com.tobiadegbuji.recipe.repositories;

import com.tobiadegbuji.recipe.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe,Long> {
}
