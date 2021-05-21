package com.codecool.woofWoofCar.Booking;

import com.codecool.woofWoofCar.Ride.Ride;
import com.codecool.woofWoofCar.User.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Transactional
    List<Booking> findAllByUser(User user);

    List<Booking> findAllByRide(Ride ride);

    @Modifying(clearAutomatically = true)
    @Query("delete from Booking b where b.ride.rideId = :id")
    void deleteBookingByRideId(long id);
}
