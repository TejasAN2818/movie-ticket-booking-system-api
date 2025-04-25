package com.example.mtb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Theater {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String theaterId;
    private String name;
    private String address;
    private String city;
    private String landmark;
    private long createdAt;
    private long updatedAt;

    private String createdBy;

    @ManyToOne
    private TheaterOwner theaterOwner;
}
