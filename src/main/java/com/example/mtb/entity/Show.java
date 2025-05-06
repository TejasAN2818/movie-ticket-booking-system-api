package com.example.mtb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Setter
@Getter
@Table(name = "Shows")
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String showId;
    private long startAt;
    private long endAt;
    private long createdAt;
    private long updatedAt;

    private String createdBy;
//    private String theaterId;
//    private String screenId;
//    private String movieId;


    @ManyToOne
    @JoinColumn(name = "theater_Id")
    private Theater theater;

    @ManyToOne
    @JoinColumn(name = "screen_Id")
    private Screen screen;

    @ManyToOne
    @JoinColumn(name = "movie_Id")
    private Movie movie;

}
