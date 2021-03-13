package com.codecool.woofWoofCar.User.Repository;

import com.codecool.woofWoofCar.User.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {
    User getByUserId(long userId);
    User getByEmail(String email);
}
