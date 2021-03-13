package com.codecool.woofWoofCar.Booking;

import com.codecool.woofWoofCar.Ride.Ride;
import com.codecool.woofWoofCar.Ride.RideService;
import com.codecool.woofWoofCar.User.Model.User;
import com.codecool.woofWoofCar.User.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookingService {
    BookingRepository bookingRepository;
    BookingStatusRepository bookingStatusRepository;
    UserService userService;
    RideService rideService;


    @Transactional
    public List<BookingStatus> getAllBookingStatuses() { return bookingStatusRepository.findAll();}

    public  BookingStatus getBookingStatusById(Long id) { return bookingStatusRepository.getOne(id);}


    public Booking addBooking(BookingRequest request) {
        Booking booking = new Booking();
        booking.setSeats(request.getSeats());
        booking.setUser(userService.getUserById(request.getUserId()));
        booking.setStatus(getBookingStatusById((long) 4));
        booking.setTotalPrice(request.getTotalPrice());
        booking.setRide(rideService.getRideById(request.getRideId()));
        return bookingRepository.save(booking);
    }


//    public Booking updateBookingStatus(BookingRequest request) {
//        Optional<Booking> booking= bookingRepository.findById(request.());
//
//        booking.get().setStatus(request.getStatus());
//        return bookingRepository.save(booking.get());
//    }

    public List<Booking> getAllBookingsByUser(User user) { return bookingRepository.findAllByUser(user);}
    public List<Booking> getAllBookingsByRide(Ride ride) { return bookingRepository.findAllByRide(ride);}

}
