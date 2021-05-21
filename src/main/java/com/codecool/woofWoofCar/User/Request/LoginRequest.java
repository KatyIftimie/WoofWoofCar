package com.codecool.woofWoofCar.User.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class LoginRequest {
    private String email, password;
//    private long userId;

}