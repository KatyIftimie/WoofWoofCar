package com.codecool.woofWoofCar.User.Model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.codecool.woofWoofCar.Ride.Ride;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @NotNull private String firstName;

    @NotNull private String lastName;

    @NotNull @Email
    @Column(unique = true) private String email;

    @NotNull private String phoneNo;

    @NotNull private String password;


    @Transient
    @OneToMany(mappedBy = "user")
    private List<Ride> rideList = new ArrayList<>();
}
