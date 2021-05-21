package com.codecool.woofWoofCar.User.Model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
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

    @NotNull @NotBlank private String firstName;

    @NotNull @NotBlank private String lastName;

    @NotNull @NotBlank @Email
    @Column(unique = true) private String email;

    @NotNull @NotBlank private String phoneNo;

    @NotNull @NotBlank
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "user_type", referencedColumnName = "userTypeId")
    private UserType type;

    public List<String> getRoles() {
        List<String> roles = new ArrayList<>();
        roles.add(getType().getName().toString());
        return roles;
    }
//    @NotNull @NotBlank private String role = "REGULAR";

    @Transient
    @OneToMany(mappedBy = "user")
    private List<Ride> rideList = new ArrayList<>();
}
