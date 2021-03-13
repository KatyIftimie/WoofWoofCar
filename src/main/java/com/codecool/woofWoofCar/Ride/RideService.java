package com.codecool.woofWoofCar.Ride;

import com.codecool.woofWoofCar.RideDetails.AnimalType;
import com.codecool.woofWoofCar.RideDetails.AnimalTypeRepository;
import com.codecool.woofWoofCar.RideDetails.CarType;
import com.codecool.woofWoofCar.RideDetails.CarTypeRepository;
import com.codecool.woofWoofCar.User.Model.User;
import com.codecool.woofWoofCar.User.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@AllArgsConstructor
public class RideService {

    RideRepository rideRepository;
    AnimalTypeRepository animalTypeRepository;
    CarTypeRepository carTypeRepository;
    UserService userService;


    @Transactional
    public List<CarType> getAllCarTypes() {
        return carTypeRepository.findAll();
    }

    public CarType getCarTypeById(long id) {
        return carTypeRepository.getOne(id);
    }

    @Transactional
    public List<AnimalType> getAllAnimalTypes() {
        return animalTypeRepository.findAll();
    }

    public AnimalType getAnimalTypeById(long id) {
        return animalTypeRepository.getOne(id);
    }

    public Ride addRide(RideRequest request) {
        Ride ride = new Ride();
        ride.setDeparture(request.getDeparture());
        ride.setArrival(request.getArrival());
        ride.setDepartureTime(request.getDepartureTime());
        ride.setUser(userService.getUserById(request.getUserId()));
        ride.setAnimalType(getAnimalTypeById(request.getAnimalTypeId()));
        ride.setCarType(getCarTypeById(request.getCarTypeId()));
        ride.setSeatsAvailable(request.getSeatsAvailable());
        ride.setPricePerSeat(request.getPricePerSeat());
        return rideRepository.save(ride);
    }

    public Ride getRideById(long id) {return rideRepository.getByRideId(id);}
    public List<Ride> getAllRides() {
        return rideRepository.findAll();
    }
    public List<Ride> getRidesByUser(User user) {
        return rideRepository.findAllByUser(user);
    }
}
