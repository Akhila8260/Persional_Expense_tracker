package com.track.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class PasswordResetToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;
    private LocalDateTime expiry;

    @ManyToOne
    private User user;

 
    public PasswordResetToken() {}

    
    public PasswordResetToken(String token, User user, LocalDateTime expiry) {
        this.token = token;
        this.user = user;
        this.expiry = expiry;
    }
}
