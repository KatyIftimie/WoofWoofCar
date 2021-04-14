package com.codecool.woofWoofCar.RideDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="animal_types")
public class AnimalType {

    public enum AnimalTypes {
        SMALL_DOG,
        MEDIUM_DOG,
        LARGE_DOG,
        CAT,
        BIRD,
        RODENT,
        ANY
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long animalId;

    @NotNull @Enumerated(EnumType.STRING) private AnimalTypes name;

}
