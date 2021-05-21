package com.codecool.woofWoofCar.User.Service;


import com.codecool.woofWoofCar.Security.JwtTokenServices;
import com.codecool.woofWoofCar.User.Model.User;
import com.codecool.woofWoofCar.User.Model.UserType;
import com.codecool.woofWoofCar.User.Repository.UserRepository;
import com.codecool.woofWoofCar.User.Repository.UserTypeRepository;
import com.codecool.woofWoofCar.User.Request.LoginRequest;
import com.codecool.woofWoofCar.User.Request.RegisterRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserTypeRepository userTypeRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenServices jwtTokenServices;
    private final AuthenticationManager authenticationManager;


    public Optional<User> getUserByEmail(String email) { return userRepository.getByEmail(email);}
    public User getUserById(long userId) {return userRepository.getByUserId(userId);}
//    public Boolean emailExists(String email) {
//        return userRepository.existsByEmail(email);
//    }
//    public User getUserByUserType(String role) {return userRepository.getUserByRole(role);
//    }

    public boolean register (RegisterRequest request) {
        boolean registered = false;
        ResponseEntity<String> validation = validateRegister(request);
        if (validation.getStatusCode().equals(HttpStatus.OK)) {
            User newUser = createUser(request);
//            System.out.println(newUser + "eeee");
            userRepository.save(newUser);
            registered = true;
        }
        return registered;
    }


    private ResponseEntity<String> validateRegister (RegisterRequest request) {
        Optional<User> email = getUserByEmail(request.getEmail());
        if (email.isPresent()) {
            return new ResponseEntity<>("Email already in use", HttpStatus.INTERNAL_SERVER_ERROR);

        }
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            return new ResponseEntity<>("Passwords don't match", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Registration successful", HttpStatus.OK);
    }

    private User createUser(RegisterRequest request) {
        User user = new User();
//        UserType userType = userTypeRepository.getOne(Long.valueOf(request.getUserTypeID()));

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPhoneNo(request.getPhoneNo());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
//        user.setType(userTypeRepository.getOne(Long.valueOf()));
        user.setType(userTypeRepository.getOne(Long.valueOf(2)));
//        System.out.println("Created user: " + user);
        return user;
    }

    public ResponseEntity<?> login(LoginRequest request, HttpServletResponse response) {
//        boolean loggedIn = false;
//        ResponseEntity<String> validation = validateLogin(request);
//        if (validation.getStatusCode().equals(HttpStatus.OK)) {
//            Optional<User> user = getUserByEmail(request.getEmail());
//            session.setAttribute("user", user);
//            loggedIn = true;
//        }
//        return loggedIn;
        try {
            String email = request.getEmail();
            System.out.println("Email : " + email + " and pass: " + request.getPassword());
//            System.out.println("eee " + new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            System.out.println("uuuuu" + authentication);
            List<String> roles = authentication.getAuthorities()
                    .stream().map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());
            System.out.println("Roles: " + roles);
            String token = jwtTokenServices.createToken(email, roles);

            Map<Object, Object> model = new HashMap<>();
            model.put("email", email);
            model.put("roles", roles);
            model.put("token", token);
            Cookie cookie = new Cookie("token", token);
            cookie.setMaxAge(604800000);
            cookie.setHttpOnly(true);
            response.addCookie(cookie);

            return ResponseEntity.ok(model);
        }catch (AuthenticationException e) {
//            throw e;
            System.out.println("pulaaaa");
//            e.printStackTrace();
            throw e;
        }
    }

    private ResponseEntity<String> validateLogin (LoginRequest request) {
        Optional<User> user = getUserByEmail(request.getEmail());
        String message = "Wrong details";
        if (user== null) {
            return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (!passwordEncoder.matches(request.getPassword(), user.get().getPassword())) {
            return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Logged in", HttpStatus.OK);
    }



}