package com.example.mtb.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String seatId;
    private String seatname;
    private long createdAt;
   // private String screenId;

    @ManyToOne
    private Screen screen;
}
