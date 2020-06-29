package com.tobiadegbuji.recipe.services;

import com.tobiadegbuji.recipe.commands.UnitOfMeasureCommand;
import java.util.Set;

public interface UnitOfMeasureService {
    Set<UnitOfMeasureCommand> getUoms();
}