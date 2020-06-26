package com.tobiadegbuji.recipe.converters;

import com.sun.istack.Nullable;
import com.tobiadegbuji.recipe.commands.UnitOfMeasureCommand;
import com.tobiadegbuji.recipe.domain.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand, UnitOfMeasure> {
    @Synchronized //Thread Safe via Lombok (Can be run in multi-threaded environment)
    @Nullable// The method can return null
    @Override
    public UnitOfMeasure convert(UnitOfMeasureCommand source) {
        if (source == null)
            return null;
        final UnitOfMeasure uom = new UnitOfMeasure();
            uom.setId(source.getId());
            uom.setUnitOfMeasure(source.getUnitOfMeasure());
            return uom;

    }
}
