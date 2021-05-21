package com.codecool.woofWoofCar.User.Repository;

import com.codecool.woofWoofCar.User.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository  extends  JpaRepository<User, Long> {
    User getByUserId(long userId);
    Optional<User> getByEmail(String email);
//    Boolean existsByEmail(String email);
//    User getUserByRole(String role);
}
