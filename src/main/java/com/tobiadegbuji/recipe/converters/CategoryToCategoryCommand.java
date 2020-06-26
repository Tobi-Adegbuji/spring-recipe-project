package com.tobiadegbuji.recipe.converters;

import com.sun.istack.Nullable;
import com.tobiadegbuji.recipe.commands.CategoryCommand;
import com.tobiadegbuji.recipe.domain.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand> {

    @Synchronized
    @Nullable
    @Override
    public CategoryCommand convert(Category source) {
        if(source == null)
        return null;
        final CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(source.getId());
        categoryCommand.setCategoryName(source.getCategoryName());
        return categoryCommand;
    }
}
