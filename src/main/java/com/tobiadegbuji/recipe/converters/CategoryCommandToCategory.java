package com.tobiadegbuji.recipe.converters;

import com.sun.istack.Nullable;
import com.tobiadegbuji.recipe.commands.CategoryCommand;
import com.tobiadegbuji.recipe.domain.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category> {

    @Synchronized
    @Nullable
    @Override
    public Category convert(CategoryCommand source) {
        if(source == null)
            return null;
        final Category category = new Category();
        category.setId(source.getId());
        category.setCategoryName(source.getCategoryName());
        return category;
    }
}
