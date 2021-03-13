package com.codecool.woofWoofCar.Booking;


import com.codecool.woofWoofCar.Ride.Ride;
import com.codecool.woofWoofCar.User.Model.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ride_id", referencedColumnName = "rideId")
    @JsonBackReference
    private Ride ride;
//    private List<Ride> reservedRides = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_status_id", referencedColumnName = "bookingStatusId")
    private BookingStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "user_id", referencedColumnName = "userId")
    @JsonBackReference
    private User user;

    private Long seats;

    private Long totalPrice;
}
