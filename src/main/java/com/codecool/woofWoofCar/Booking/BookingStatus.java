package com.codecool.woofWoofCar.Booking;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table
public class BookingStatus {
    public enum Status {
        BOOKED, AWAITING, COMPLETED, CANCELED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingStatusId;
    @NotNull
    @Enumerated(EnumType.STRING) private Status name = Status.AWAITING;
}
