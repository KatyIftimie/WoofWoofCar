package com.codecool.woofWoofCar.User.Request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class RegisterRequest {
    private String firstName, lastName, email, phoneNo, password, confirmPassword;
}