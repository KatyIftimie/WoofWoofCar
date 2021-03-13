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
@Table(name = "car_types")
public class CarType {

    public enum CarTypes {
        SMALL, BREAK, MINIVAN
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carId;
    @NotNull @Enumerated(EnumType.STRING) private CarTypes name;
}
