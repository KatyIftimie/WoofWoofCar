package com.codecool.woofWoofCar.User.Service;


import com.codecool.woofWoofCar.User.Model.User;
import com.codecool.woofWoofCar.User.Repository.UserRepository;
import com.codecool.woofWoofCar.User.Request.LoginRequest;
import com.codecool.woofWoofCar.User.Request.RegisterRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpSession;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

//
//    public User getUserByUsername(String username) {
//        return userRepository.getByUsername(username);
//    }
    public User getUserByEmail(String email) { return userRepository.getByEmail(email);}
    public User getUserById(long userId) {return userRepository.getByUserId(userId);}

    public boolean register (RegisterRequest request) {
        boolean registered = false;
        ResponseEntity<String> validation = validateRegister(request);
        if (validation.getStatusCode().equals(HttpStatus.OK)) {
            User newUser = createUser(request);
            userRepository.save(newUser);
            registered = true;
        }
        return registered;
    }


    private ResponseEntity<String> validateRegister (RegisterRequest request) {
//        User userName = getUserByUsername(request.getUsername());
        User email = getUserByEmail(request.getEmail());
//        if (userName != null) {
//            return new ResponseEntity<>("Username already in use", HttpStatus.INTERNAL_SERVER_ERROR);
//
//        }
        if (email != null) {
            return new ResponseEntity<>("Email already in use", HttpStatus.INTERNAL_SERVER_ERROR);

        }
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            return new ResponseEntity<>("Password don't match", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Registration successful", HttpStatus.OK);
    }

    private User createUser(RegisterRequest request) {
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
//        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPhoneNo(request.getPhoneNo());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return user;
    }

    public boolean login(LoginRequest request, HttpSession session) {
        boolean loggedIn = false;
        ResponseEntity<String> validation = validateLogin(request);
        if (validation.getStatusCode().equals(HttpStatus.OK)) {
            User user = getUserByEmail(request.getEmail());
            session.setAttribute("user", user);
            loggedIn = true;
        }
        return loggedIn;
    }

    private ResponseEntity<String> validateLogin (LoginRequest request) {
        User user = getUserByEmail(request.getEmail());
        String message = "Wrong details";
        if (user== null) {
            return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Logged in", HttpStatus.OK);
    }



}