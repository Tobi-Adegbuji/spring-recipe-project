package com.tobiadegbuji.recipe.repositories;

import com.tobiadegbuji.recipe.domain.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
