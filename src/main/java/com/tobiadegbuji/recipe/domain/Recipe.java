package com.tobiadegbuji.recipe.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private Integer prepTime;

    private Integer cookTime;

    private Integer servings;

    private String source;

    private String url;

    @Lob
    private String directions;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<Ingredient> ingredients = new HashSet<>();

    @Lob //Will be created as a BLOB field in the database
    private Byte[] image;

    @Enumerated(value = EnumType.STRING)
    //Using STRING instead of ORDINAL so that future changes to the enum will not affect anything. Ordinal values get persisted with numbers instead of the String.
    private Difficulty difficulty;

    @OneToOne(cascade = CascadeType.ALL) //If we delete a Recipe object, the notes object gets deleted as well.
    private Notes notes;

    @ManyToMany
    @JoinTable(name = "recipe_category",
            joinColumns = @JoinColumn(name = "recipe_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();


    public Recipe() {
    }


    public Recipe addIngredient(Ingredient ingredient) {
        ingredient.setRecipe(this); //So we dont have to set it ourselves outside of the class. Did the same for Notes
        this.ingredients.add(ingredient);
        return this;
    }


    protected boolean canEqual(final Object other) {
        return other instanceof Recipe;
    }

}


//Its usually common convention to put your entities in a package called "domain" or "model"
