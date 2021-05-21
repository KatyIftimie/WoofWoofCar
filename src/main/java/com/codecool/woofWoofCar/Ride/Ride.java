package com.codecool.woofWoofCar.Ride;


import com.codecool.woofWoofCar.RideDetails.AnimalType;
import com.codecool.woofWoofCar.RideDetails.CarType;
import com.codecool.woofWoofCar.User.Model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="rides")
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long rideId;

//    @NotNull
//    private String fromLong;
//    @NotNull
//    private String fromLat;
//
//    @NotNull
//    private String toLong;
//    @NotNull
//    private String toLat;

    @NotNull
    private String departure;

    @NotNull String arrival;

    @NotNull
    private LocalDateTime departureTime;

    @NotNull
    private Long seatsAvailable;

    @NotNull
    private Long pricePerSeat;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private User user;

//    @Transient
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "animal_type_id", referencedColumnName = "animalId")
    private List<AnimalType> animalTypes = new ArrayList<>();

//    @Transient
//    @OneToMany(mappedBy = "user")
//    private List<Ride> rideList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_type_id", referencedColumnName = "carId")
    private CarType carType;


    public void addAnimalType(AnimalType animalType) {
        animalTypes.add(animalType);
    }
}
