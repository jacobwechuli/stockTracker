package com.stockportfolio.manager.controller;

import com.stockportfolio.manager.model.User;
import com.stockportfolio.manager.security.JwtUtil;
import com.stockportfolio.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
        Optional<User> user = userService.findByUsername(username);

        if (user.isPresent()) {
            if (user.get().getPassword().equals(password)) {
            return ResponseEntity.ok("Login Successful");
        } else {
                return ResponseEntity.status(401).body("Wrong password");
            }} else {
                return ResponseEntity.status(401).body("Invalid Credentials");
            }
    }
}
