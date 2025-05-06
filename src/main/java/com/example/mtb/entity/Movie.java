package com.example.mtb.entity;

import com.example.mtb.enums.Certificate;
import com.example.mtb.enums.Genre;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Setter
@Getter
public class Movie {


    @Id
    private String movieId;
    private String title;
    private String descripation;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> cast;
    private long runtime;
    @Enumerated(EnumType.STRING)
    private Certificate certificate;
    @Enumerated(EnumType.STRING)
    private Genre genre;

    @OneToMany(mappedBy = "movie")
    private List<Show> shows;

    @OneToMany(mappedBy = "movie", fetch = FetchType.EAGER)
    private List<Feedback> feedbackList;

}
