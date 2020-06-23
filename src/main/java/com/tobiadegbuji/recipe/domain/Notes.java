package com.tobiadegbuji.recipe.domain;

import lombok.*;

import javax.persistence.*;

@Data
@EqualsAndHashCode(exclude="recipe")
@Entity
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Recipe recipe;

    @Lob //Will be stored in CLOB field in database. This allows users to write more than 256 characters for the notes.
    private String recipeNotes;

}
