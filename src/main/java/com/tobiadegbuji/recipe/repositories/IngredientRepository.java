package com.tobiadegbuji.recipe.repositories;

import com.tobiadegbuji.recipe.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient,Long> {

}
