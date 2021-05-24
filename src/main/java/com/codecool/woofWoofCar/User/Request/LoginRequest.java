package com.codecool.woofWoofCar.User.Request;

import lombok.Data;


@Data
public class LoginRequest {
    private String email, password;

}