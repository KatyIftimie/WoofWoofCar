package com.codecool.woofWoofCar.Ride;

import lombok.Data;


import java.time.LocalDateTime;
import java.util.List;

@Data
public class RideRequest {
    private String departure, arrival;
    private Long carTypeId;
    private List<Long> animalTypeIds;
    private LocalDateTime departureTime;
    private long seatsAvailable;
    private long userId;
    private Long pricePerSeat;
}
