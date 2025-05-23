package com.example.mtb.entity;

import com.example.mtb.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Setter
@Getter
public class UserDetails {

//a
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String userId;
    private String username;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private Long createdAt;
    private Long updatedAt;
    private boolean isDelete;
    private Instant deletedAt;
}
