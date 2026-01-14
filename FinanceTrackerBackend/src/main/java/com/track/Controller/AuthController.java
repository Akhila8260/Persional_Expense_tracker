package com.track.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.track.Entity.User;
import com.track.Service.AuthService;
import com.track.dto.AuthResponse;
@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {
	
	@Autowired
    private AuthService authService;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return authService.register(user);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody User user) {

        User authenticatedUser =
                authService.login(user.getEmail(), user.getPassword());

        String token = authService.generateToken(authenticatedUser);

        return new AuthResponse(token, authenticatedUser.getId());
    }

}
