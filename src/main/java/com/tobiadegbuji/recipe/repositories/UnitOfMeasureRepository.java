package com.tobiadegbuji.recipe.repositories;

import com.tobiadegbuji.recipe.commands.UnitOfMeasureCommand;
import com.tobiadegbuji.recipe.domain.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {

    Optional<UnitOfMeasure> findByUnitOfMeasure(String uom);

}
