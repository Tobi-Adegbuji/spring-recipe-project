package com.tobiadegbuji.recipe.commands;

import com.tobiadegbuji.recipe.domain.Category;
import com.tobiadegbuji.recipe.domain.Difficulty;
import com.tobiadegbuji.recipe.domain.Ingredient;
import com.tobiadegbuji.recipe.domain.Notes;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
public class RecipeCommand {
    private Long id;

    @NotBlank
    @Size(min = 3, max =225)
    private String description;

    @Min(1)
    @Max(1000)
    private Integer prepTime;

    @Min(1)
    @Max(1000)
    private Integer cookTime;

    @Min(1)
    @Max(1000)
    private Integer servings;

    @NotBlank
    @URL
    private String url;

    @NotBlank
    private String directions;

    private String source;

    private Set<IngredientCommand> ingredients = new HashSet<>();
    private Byte[] image;
    private Difficulty difficulty;
    private NotesCommand notes;
    private Set<CategoryCommand> categories = new HashSet<>();
}
