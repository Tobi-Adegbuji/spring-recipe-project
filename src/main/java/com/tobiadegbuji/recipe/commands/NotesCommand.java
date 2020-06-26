package com.tobiadegbuji.recipe.commands;

import com.tobiadegbuji.recipe.domain.Recipe;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class NotesCommand {
    private Long id;
    private String recipeNotes;
}
