package com.codecool.woofWoofCar.User.Controller;

import com.codecool.woofWoofCar.User.Model.User;
import com.codecool.woofWoofCar.User.Request.LoginRequest;
import com.codecool.woofWoofCar.User.Request.RegisterRequest;
import com.codecool.woofWoofCar.User.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @GetMapping
    public boolean welcome() {
        return true;
    }

    @PostMapping("/register")
    public boolean registerUser(@RequestBody RegisterRequest request) {
        return userService.register(request);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest request, HttpServletResponse response) {
        return userService.login(request, response);
    }

    @GetMapping("/logout")
    public void logoutUser(HttpSession session, HttpServletResponse response) throws IOException {
        session.removeAttribute("user");
        response.sendRedirect("/");
    }

    @GetMapping("/get-user/{email}")
    public Optional<User> getUserByEmail(@PathVariable String email) {
            Optional<User> user = userService.getUserByEmail(email);
            return user;
    }

    @PutMapping("/update-user")
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        User updateUser = userService.updateUser(user);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }
}
