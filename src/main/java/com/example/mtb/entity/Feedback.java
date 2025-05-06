package com.example.mtb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String feedbackId;
    private int ratings;
    private String review;
    private long createdAt;

    @ManyToOne
    private User user;

    @ManyToOne
    private Movie movie;

}
