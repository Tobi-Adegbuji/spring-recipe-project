package com.tobiadegbuji.recipe.converters;

import com.sun.istack.Nullable;
import com.tobiadegbuji.recipe.commands.IngredientCommand;
import com.tobiadegbuji.recipe.domain.Ingredient;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {

    @Synchronized
    @Nullable
    @Override
    public Ingredient convert(IngredientCommand source) {
        if(source == null)
        return null;
        final Ingredient ingredient = new Ingredient();
        ingredient.setId(source.getId());
        ingredient.setAmount(source.getAmount());
        ingredient.setDescription(source.getDescription());
        ingredient.setUnitOfMeasure(source.getUnitOfMeasure());
        return ingredient;
    }
}
