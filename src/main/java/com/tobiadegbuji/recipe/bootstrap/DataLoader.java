package com.tobiadegbuji.recipe.bootstrap;

import com.tobiadegbuji.recipe.domain.*;
import com.tobiadegbuji.recipe.repositories.CategoryRepository;
import com.tobiadegbuji.recipe.repositories.RecipeRepository;
import com.tobiadegbuji.recipe.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private RecipeRepository recipeRepository;
    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public DataLoader(RecipeRepository recipeRepository, CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());
        log.debug("Loaded Bootstrap");

    }

    private List<Recipe> getRecipes(){


        List<Recipe> recipes = new ArrayList<>(2);

        //Getting the Unit Of Measures

        Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByUnitOfMeasure("each");
        if (!eachUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM was not found");
        }

        Optional<UnitOfMeasure> tableSpoonUomOptional = unitOfMeasureRepository.findByUnitOfMeasure("tablespoon");
        if (!tableSpoonUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM was not found");
        }

        Optional<UnitOfMeasure> teaSpoonUomOptional = unitOfMeasureRepository.findByUnitOfMeasure("teaspoon");
        if (!teaSpoonUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM was not found");
        }

        Optional<UnitOfMeasure> dashUomOptional = unitOfMeasureRepository.findByUnitOfMeasure("dash");
        if (!dashUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM was not found");
        }

        Optional<UnitOfMeasure> pintUomOptional = unitOfMeasureRepository.findByUnitOfMeasure("pint");
        if (!pintUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM was not found");
        }

        Optional<UnitOfMeasure> cupUomOptional = unitOfMeasureRepository.findByUnitOfMeasure("cup");
        if (!cupUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM was not found");
        }

        //Getting the optionals
        UnitOfMeasure eachUom = eachUomOptional.get();
        UnitOfMeasure tableSpoonUom = tableSpoonUomOptional.get();
        UnitOfMeasure teaSpoonUom = teaSpoonUomOptional.get();
        UnitOfMeasure dashUom = dashUomOptional.get();
        UnitOfMeasure pintUom = pintUomOptional.get();
        UnitOfMeasure cupUom = cupUomOptional.get();

        //Getting the categories
        Optional<Category> americanCategoryOptional = categoryRepository.findByCategoryName("American");
        if (!americanCategoryOptional.isPresent()) {
            throw new RuntimeException("Expected Category not found");
        }

        Optional<Category> mexicanCategoryOptinal = categoryRepository.findByCategoryName("Mexican");
        if (!mexicanCategoryOptinal.isPresent()) {
            throw new RuntimeException("Expected Category not found");
        }

        //Getting the optionals
        Category americanCategory = americanCategoryOptional.get();
        Category mexicanCategory = mexicanCategoryOptinal.get();


        Recipe perfectGuacamole = new Recipe();
        perfectGuacamole.setCookTime(0);
        perfectGuacamole.setDescription("Perfect Guacamole");
        perfectGuacamole.setDifficulty(Difficulty.EASY);
        perfectGuacamole.setDirections("1 Cut the avocado, remove flesh: Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\n" +
                "4 Serve: Serve immediately, or if making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.");
        perfectGuacamole.setPrepTime(10);
        perfectGuacamole.setPrepTime(10);
        perfectGuacamole.setServings(4);
        perfectGuacamole.getCategories().add(americanCategory);
        perfectGuacamole.getCategories().add(mexicanCategory);
        Notes perfectGuacamoleNotes = new Notes();
        perfectGuacamoleNotes.setRecipe(perfectGuacamole);
        perfectGuacamoleNotes.setRecipeNotes("This is my note for perfect Guac!");
        perfectGuacamole.setNotes(perfectGuacamoleNotes);

        //Get Ingredients returns a set to allow us to add new ingredients for the recipe
        perfectGuacamole.addIngredient(new Ingredient("ripe avocados", new BigDecimal(2), eachUom));
        perfectGuacamole.addIngredient(new Ingredient("Kosher salt", new BigDecimal(".5"), tableSpoonUom));
        perfectGuacamole.addIngredient(new Ingredient("fresh lime or lemon juice", new BigDecimal(2), tableSpoonUom));
        perfectGuacamole.addIngredient(new Ingredient("minced red onion or thinly sliced green onion", new BigDecimal(2), tableSpoonUom));
        perfectGuacamole.addIngredient(new Ingredient("serrano chiles, stems/seeds removed, minced", new BigDecimal(2), eachUom));
        perfectGuacamole.addIngredient(new Ingredient("Cilantro", new BigDecimal(2), tableSpoonUom));
        perfectGuacamole.addIngredient(new Ingredient("freshly grated black pepper", new BigDecimal(2), dashUom));
        perfectGuacamole.addIngredient(new Ingredient("ripe tomato, seeds & pulp removed, chopped", new BigDecimal(.5), eachUom));

        perfectGuacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        perfectGuacamole.setSource("Simply Recipe");

        recipes.add(perfectGuacamole);

        Recipe spicyGrilledChickenTaco = new Recipe();
        spicyGrilledChickenTaco.setDescription("Spicy Grilled Chicken Taco");
        spicyGrilledChickenTaco.setCookTime(9);
        spicyGrilledChickenTaco.setPrepTime(20);
        spicyGrilledChickenTaco.setServings(5);
        spicyGrilledChickenTaco.setDifficulty(Difficulty.MEDIUM);
        spicyGrilledChickenTaco.setDirections("To be Added");

        Notes spicyGrilledChickenNotes = new Notes();
        spicyGrilledChickenNotes.setRecipe(spicyGrilledChickenTaco);
        spicyGrilledChickenNotes.setRecipeNotes("This some notes for the Spicy Grilled Chicken Recipe");
        spicyGrilledChickenTaco.setNotes(spicyGrilledChickenNotes);

        spicyGrilledChickenTaco.addIngredient(new Ingredient("Ancho Chili Powder", new BigDecimal(2), tableSpoonUom));
        spicyGrilledChickenTaco.addIngredient(new Ingredient("Dried Oregano", new BigDecimal(1), teaSpoonUom));
        spicyGrilledChickenTaco.addIngredient(new Ingredient("Dried Cumin", new BigDecimal(1), teaSpoonUom));
        spicyGrilledChickenTaco.addIngredient(new Ingredient("Sugar", new BigDecimal(1), teaSpoonUom));
        spicyGrilledChickenTaco.addIngredient(new Ingredient("Salt", new BigDecimal(".5"), teaSpoonUom));
        spicyGrilledChickenTaco.addIngredient(new Ingredient("Clove of Garlic, Chopped", new BigDecimal(1), eachUom));
        spicyGrilledChickenTaco.addIngredient(new Ingredient("finely grated orange zest", new BigDecimal(1), tableSpoonUom));
        spicyGrilledChickenTaco.addIngredient(new Ingredient("fresh-squeezed orange juice", new BigDecimal(3), tableSpoonUom));
        spicyGrilledChickenTaco.addIngredient(new Ingredient("Olive oil", new BigDecimal(2), tableSpoonUom));
        spicyGrilledChickenTaco.addIngredient(new Ingredient("bonless chicken thighs", new BigDecimal(4), tableSpoonUom ));
        spicyGrilledChickenTaco.addIngredient(new Ingredient("small corn tortillas", new BigDecimal(8), eachUom));
        spicyGrilledChickenTaco.addIngredient(new Ingredient("packed baby arugula", new BigDecimal(3),cupUom ));
        spicyGrilledChickenTaco.addIngredient(new Ingredient("medium ripe avocados, sliced", new BigDecimal(2),eachUom ));
        spicyGrilledChickenTaco.addIngredient(new Ingredient("radishes, thinly sliced", new BigDecimal(4), eachUom ));
        spicyGrilledChickenTaco.addIngredient(new Ingredient("cherry tomatoes halved", new BigDecimal(".5"), pintUom));
        spicyGrilledChickenTaco.addIngredient(new Ingredient("red onion, thinly sliced", new BigDecimal(".25"),eachUom ));
        spicyGrilledChickenTaco.addIngredient(new Ingredient("Roughly Chopped cilantro", new BigDecimal(4), eachUom ));
        spicyGrilledChickenTaco.addIngredient(new Ingredient("sour cream thinned with 1/4 cup milk", new BigDecimal(4), cupUom));
        spicyGrilledChickenTaco.addIngredient(new Ingredient("lim, cut into wedges", new BigDecimal(4), eachUom));

        spicyGrilledChickenTaco.getCategories().add(mexicanCategory);
        spicyGrilledChickenTaco.getCategories().add(americanCategory);

        spicyGrilledChickenTaco.setSource("Simply Recipe");
        spicyGrilledChickenTaco.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");

        recipes.add(spicyGrilledChickenTaco);

        return recipes;

    }
}
//perfectGuacamole.setDirections("1 Cut the avocado, remove flesh: Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\n" +
//        "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n" +
//        "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
//        "\n" +
//        "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
//        "\n" +
//        "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
//        "\n" +
//        "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\n" +
//        "4 Serve: Serve immediately, or if making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.");
//        perfectGuacamole.setPrepTime(10);