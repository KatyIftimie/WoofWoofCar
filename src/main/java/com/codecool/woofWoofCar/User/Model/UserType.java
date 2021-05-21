package com.codecool.woofWoofCar.User.Model;

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
@Entity @Table(name= "user_types")
public class UserType {

    public enum UserTypeRoles {
        REGULAR,
        ADMIN
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userTypeId;

    @NotNull @Enumerated(EnumType.STRING) private UserTypeRoles name;
    private String description;

    @Override
    public String toString() {
        return "" + name;
    }
}
