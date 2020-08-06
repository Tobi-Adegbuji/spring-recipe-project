package com.tobiadegbuji.recipe.commands;

import com.tobiadegbuji.recipe.domain.Category;
import com.tobiadegbuji.recipe.domain.Difficulty;
import com.tobiadegbuji.recipe.domain.Ingredient;
import com.tobiadegbuji.recipe.domain.Notes;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
public class RecipeCommand {
    private Long id;

    //Validators
    @NotBlank
    @Size(min = 3, max =225)
    private String description;

    @NotNull(message= "You must input a preparation time.")
    @Min(1)
    @Max(1000)
    private Integer prepTime;

    @NotNull(message= "You must input a cook time.")
    @Min(1)
    @Max(1000)
    private Integer cookTime;

    @Min(1)
    @Max(100)
    private Integer servings;

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
