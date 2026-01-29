package com.track.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

import com.track.Entity.PasswordResetToken;
import com.track.Entity.User;
import com.track.Service.AuthService;
import com.track.dto.AuthResponse;
import com.track.repository.PasswordResetTokenRepository;
@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {
	
	@Autowired
    private AuthService authService;
	
	@Autowired
	private PasswordResetTokenRepository tokenRepository;

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody User user) {

	    try {
	        return ResponseEntity.ok(authService.register(user));
	    }
	    catch (RuntimeException ex) {

	        if (ex.getMessage().equals("Email already exists")) {
	            return ResponseEntity
	                    .status(HttpStatus.CONFLICT)
	                    .body("User already exists");
	        }

	        return ResponseEntity
	                .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Registration failed");
	    }
	}


    @PostMapping("/login")
    public AuthResponse login(@RequestBody User user) {

        User authenticatedUser =
                authService.login(user.getEmail(), user.getPassword());

        String token = authService.generateToken(authenticatedUser);

        return new AuthResponse(token, authenticatedUser.getId());
    }
    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody Map<String,String> req) {

        String email = req.get("email");

        User user = authService.findByEmailOptional(email)
                .orElseThrow(() -> new RuntimeException("User not found"));


        String token = UUID.randomUUID().toString();

        PasswordResetToken resetToken = new PasswordResetToken(
                token,
                user,
                LocalDateTime.now().plusMinutes(15)
        );

        tokenRepository.save(resetToken);

        // For now just print link
        System.out.println("Reset link: http://localhost:4200/reset-password?token=" + token);

        return ResponseEntity.ok("Reset link sent");
    }
    
    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody Map<String,String> req) {

        String token = req.get("token");
        String newPassword = req.get("password");

        PasswordResetToken resetToken = tokenRepository.findByToken(token)
            .orElseThrow(() -> new RuntimeException("Invalid token"));

        if (resetToken.getExpiry().isBefore(LocalDateTime.now())) {
            return ResponseEntity.badRequest().body("Token expired");
        }

        User user = resetToken.getUser();
        user.setPassword(authService.encodePassword(newPassword));
        authService.save(user);

        tokenRepository.delete(resetToken);

        return ResponseEntity.ok("Password updated successfully");
    }



}
