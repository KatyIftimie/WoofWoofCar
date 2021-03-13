package com.codecool.woofWoofCar.Ride;

import com.codecool.woofWoofCar.User.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RideRepository extends JpaRepository<Ride, Long> {
    List<Ride> findAllByUser(User user);
//    Ride findAllByUser(long rideId);
//    List<Ride> getAllBy();
    List<Ride> findAllByDeparture(String departure);
    List<Ride> findAllByArrival(String arrival);
    List<Ride> findAllByDepartureAndArrival(String departure, String arrival);

    Ride getByRideId(long id);
}
