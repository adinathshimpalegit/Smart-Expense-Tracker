
package com.aditech.expensetracker.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.aditech.expensetracker.entity.User;
import com.aditech.expensetracker.repository.UserRepository;
import com.aditech.expensetracker.security.JwtUtil;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole("USER");
        userRepository.save(user);
        return "User Registered";
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        User existing = userRepository.findByUsername(user.getUsername()).orElseThrow();
        if (encoder.matches(user.getPassword(), existing.getPassword())) {
            return jwtUtil.generateToken(existing.getUsername());
        }
        return "Invalid Credentials";
    }
}
