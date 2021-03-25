package com.codecool.woofWoofCar.User.Controller;

import com.codecool.woofWoofCar.User.Model.User;
import com.codecool.woofWoofCar.User.Request.LoginRequest;
import com.codecool.woofWoofCar.User.Request.RegisterRequest;
import com.codecool.woofWoofCar.User.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public boolean registerUser(@RequestBody RegisterRequest request) {
        return userService.register(request);
    }

    @PostMapping("/login")
    public boolean loginUser(@RequestBody LoginRequest request, HttpSession session) {
        return userService.login(request, session);
    }

    @GetMapping("/logout")
    public void logoutUser(HttpSession session, HttpServletResponse response) throws IOException {
        session.removeAttribute("user");
        response.sendRedirect("/login");
    }

    @GetMapping("/get-user/{email}")
    public Optional<User> getUserByEmail(@PathVariable String email) {
            Optional<User> user = Optional.ofNullable(userService.getUserByEmail(email));
            return user;
    }
}
