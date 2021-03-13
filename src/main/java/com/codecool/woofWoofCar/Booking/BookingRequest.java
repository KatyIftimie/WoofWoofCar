package com.codecool.woofWoofCar.Booking;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class BookingRequest {
//    private Long bookingId;
//    private Long bookingStatusId;
    private BookingStatus status;
    private Long seats;
    private Long totalPrice;
    private long userId;
    private Long rideId;

}
