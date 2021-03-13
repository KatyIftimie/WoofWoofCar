package com.codecool.woofWoofCar.Ride;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class RideRequest {
    private String departure, arrival;
    private Long carTypeId;
    private Long animalTypeId;
    private LocalDateTime departureTime;
    private long seatsAvailable;
    private long userId;
    private Long pricePerSeat;
}
