package com.codecool.woofWoofCar.User.Request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Setter
@Getter
public class RegisterRequest {
    private String firstName, lastName, email, phoneNo, password, confirmPassword, sex, aboutMe;
    private LocalDate birthDate;
}