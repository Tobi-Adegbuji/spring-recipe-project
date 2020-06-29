package com.tobiadegbuji.recipe.services;

import com.tobiadegbuji.recipe.commands.UnitOfMeasureCommand;
import com.tobiadegbuji.recipe.domain.UnitOfMeasure;

import java.util.Set;

public interface UnitOfMeasureService {
    Set<UnitOfMeasureCommand> getUoms();

    UnitOfMeasure findFirstUom();

}