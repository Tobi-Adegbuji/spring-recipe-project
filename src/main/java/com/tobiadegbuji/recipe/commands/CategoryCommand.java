package com.tobiadegbuji.recipe.commands;

import com.tobiadegbuji.recipe.domain.Recipe;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ManyToMany;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CategoryCommand {
    private Long id;
    private String categoryName;
}
