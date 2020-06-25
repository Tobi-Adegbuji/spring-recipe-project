package com.tobiadegbuji.recipe.repositories;

import com.tobiadegbuji.recipe.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class UnitOfMeasureRepositoryIT {

    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DirtiesContext //Reloads context
    void findByUnitOfMeasureEach() throws Exception{
        Optional<UnitOfMeasure> uomOptional = unitOfMeasureRepository.findByUnitOfMeasure("each");
        assertEquals("each",uomOptional.get().getUnitOfMeasure());
    }

    @Test
    void findByUnitOfMeasureCup() throws Exception{
        Optional<UnitOfMeasure> uomOptional = unitOfMeasureRepository.findByUnitOfMeasure("cup");
        assertEquals("cup",uomOptional.get().getUnitOfMeasure());
    }
}