package com.codecool.woofWoofCar.Ride;

import com.codecool.woofWoofCar.User.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RideRepository extends JpaRepository<Ride, Long> {
    List<Ride> findAllByUser(User user);

    List<Ride> findAllByDeparture(String departure);

    List<Ride> findAllByArrival(String arrival);

    List<Ride> findAllByDepartureAndArrival(String departure, String arrival);

    Ride getByRideId(long id);

    @Modifying(clearAutomatically = true)
    @Query("delete from Ride r where r.rideId = :id")
    void deleteRideByRideId(long id);

}