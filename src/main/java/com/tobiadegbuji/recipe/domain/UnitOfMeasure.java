package com.tobiadegbuji.recipe.domain;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@Entity
public class UnitOfMeasure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String unitOfMeasure;

}
