package com.tobiadegbuji.recipe.repositories;

import com.tobiadegbuji.recipe.domain.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

    //Utilizing findBy since we just want to return one category
    Optional<Category> findByCategoryName(String categoryName);

}
