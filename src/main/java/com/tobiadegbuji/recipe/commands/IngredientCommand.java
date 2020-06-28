package com.tobiadegbuji.recipe.commands;

import com.tobiadegbuji.recipe.domain.Recipe;
import com.tobiadegbuji.recipe.domain.UnitOfMeasure;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
public class IngredientCommand {
    private Long id;
    private Long recipeId;
    private String description;
    private BigDecimal amount;
    private UnitOfMeasure unitOfMeasure;
}
