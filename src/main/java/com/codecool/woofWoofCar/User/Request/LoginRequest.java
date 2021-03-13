package com.codecool.woofWoofCar.User.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class LoginRequest {
    private String email, password;
    private long userId;

}