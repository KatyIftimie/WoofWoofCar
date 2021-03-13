package com.codecool.woofWoofCar.Booking;

import com.codecool.woofWoofCar.Ride.Ride;
import com.codecool.woofWoofCar.Ride.RideService;
import com.codecool.woofWoofCar.User.Model.User;
import com.codecool.woofWoofCar.User.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/booking")
public class BookingController {

    private final BookingService bookingService;
    private final UserService userService;
    private final RideService rideService;


    @GetMapping("/bookings-by-user/{userId}")
    public List<Booking> getUsersBookings(@PathVariable("userId") long userId) {
        User user = userService.getUserById(userId);
        return bookingService.getAllBookingsByUser(user);
    }

    @GetMapping("/bookings-by-ride/{rideId}")
    public List<Booking> getRidesBookings(@PathVariable("rideId") long rideId) {
        Ride ride = rideService.getRideById(rideId);
        return bookingService.getAllBookingsByRide(ride);
    }

    @PostMapping("/add-booking")
    public ResponseEntity<String> addBooking(@RequestBody BookingRequest request) {
        Booking bookingToAdd = bookingService.addBooking(request);
        return new ResponseEntity<>(Long.toString(bookingToAdd.getBookingId()), HttpStatus.CREATED);
    }

}
