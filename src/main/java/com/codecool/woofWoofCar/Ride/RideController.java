package com.codecool.woofWoofCar.Ride;

import com.codecool.woofWoofCar.RideDetails.AnimalType;
import com.codecool.woofWoofCar.RideDetails.CarType;
import com.codecool.woofWoofCar.User.Model.User;
import com.codecool.woofWoofCar.User.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/ride")
public class RideController {

    private final RideService rideService;
    private final UserService userService;


    @GetMapping("/rides")
    public List<Ride> getAllRides() {
        return rideService.getAllRides();
    }

    @GetMapping("/animal-types")
    public List<AnimalType> getAllAnimalTypes() {
        return rideService.getAllAnimalTypes();
    }

    @GetMapping("/car-types")
    public List<CarType> getAllCarTypes() {
        return rideService.getAllCarTypes();
    }

    @Transactional
    @GetMapping("/rides/{userId}")
    public List<Ride> getUsersRides(@PathVariable("userId") long userId) {
        User user= userService.getUserById(userId);
        return rideService.getRidesByUser(user);
    }

    @PostMapping("/add-ride")
    public ResponseEntity<String> addRide(@RequestBody RideRequest request) {
        Ride addedRide=rideService.addRide(request);
        return new ResponseEntity<>(Long.toString(addedRide.getRideId()), HttpStatus.CREATED);
    }
}
