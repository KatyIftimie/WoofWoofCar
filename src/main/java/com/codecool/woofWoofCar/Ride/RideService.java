package com.codecool.woofWoofCar.Ride;

import com.codecool.woofWoofCar.Booking.BookingRepository;
import com.codecool.woofWoofCar.RideDetails.*;
import com.codecool.woofWoofCar.User.Model.User;
import com.codecool.woofWoofCar.User.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@AllArgsConstructor
public class RideService {

    RideRepository rideRepository;
    AnimalTypeRepository animalTypeRepository;
    AnimalTypeService animalTypeService;
    CarTypeRepository carTypeRepository;
    UserService userService;
    BookingRepository bookingRepository;
//    RideRequest rideRequest;


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
        System.out.println("eeee");
        Ride ride = new Ride();
        ride.setDeparture(request.getDeparture());
        ride.setArrival(request.getArrival());
        ride.setDepartureTime(request.getDepartureTime());
        ride.setUser(userService.getUserById(request.getUserId()));

        request.getAnimalTypeIds().forEach(ID -> {
            AnimalType animalType = animalTypeService.getAnimalTypeById(ID);
            System.out.println("row 54");
            ride.addAnimalType(animalType);
        });


//        ride.setAnimalType(getAnimalTypeById(request.getAnimalTypeId()));
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


    public void deleteRideById(long id) {
        bookingRepository.deleteBookingByRideId(id);
        rideRepository.deleteRideByRideId(id);
    }
}
