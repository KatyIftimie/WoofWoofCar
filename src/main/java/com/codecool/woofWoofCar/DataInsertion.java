package com.codecool.woofWoofCar;

import com.codecool.woofWoofCar.Booking.BookingStatus;
import com.codecool.woofWoofCar.Booking.BookingStatusRepository;
import com.codecool.woofWoofCar.RideDetails.AnimalType;
import com.codecool.woofWoofCar.RideDetails.AnimalTypeRepository;
import com.codecool.woofWoofCar.RideDetails.CarType;
import com.codecool.woofWoofCar.RideDetails.CarTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DataInsertion {
    CarTypeRepository carTypeRepository;
    AnimalTypeRepository animalTypeRepository;
    BookingStatusRepository bookingStatusRepository;

    public void addData1() {
        CarType c1= new CarType();
        c1.setName(CarType.CarTypes.BREAK);
        carTypeRepository.save(c1);

        CarType c2= new CarType();
        c2.setName(CarType.CarTypes.MINIVAN);
        carTypeRepository.save(c2);

        CarType c3= new CarType();
        c3.setName(CarType.CarTypes.SMALL);
        carTypeRepository.save(c3);

        AnimalType a1 = new AnimalType();
        a1.setName(AnimalType.AnimalTypes.CAT);
        animalTypeRepository.save(a1);

        AnimalType a2 = new AnimalType();
        a2.setName(AnimalType.AnimalTypes.DOG);
        animalTypeRepository.save(a2);

        AnimalType a3 = new AnimalType();
        a3.setName(AnimalType.AnimalTypes.OTHER);
        animalTypeRepository.save(a3);

        BookingStatus b1 = new BookingStatus();
        b1.setName(BookingStatus.Status.BOOKED);
        bookingStatusRepository.save(b1);

        BookingStatus b2 = new BookingStatus();
        b2.setName(BookingStatus.Status.COMPLETED);
        bookingStatusRepository.save(b2);

        BookingStatus b3 = new BookingStatus();
        b3.setName(BookingStatus.Status.CANCELED);
        bookingStatusRepository.save(b3);


        BookingStatus b4 = new BookingStatus();
        b4.setName(BookingStatus.Status.AWAITING);
        bookingStatusRepository.save(b4);
    }
}
