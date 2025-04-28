package com.example.mtb.entity;

import com.example.mtb.enums.ScreenType;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
public class Screen {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String screenId;
    @Enumerated(EnumType.STRING)
    private ScreenType screenType;
    private Integer capacity;
    private Integer noOfRows;
    private Long createdAt;
    private Long updatedAt;
    private String createdBy;
    private String theaterId;

    @ManyToOne
    private Theater theater;

    @OneToMany(mappedBy = "screen", fetch = FetchType.EAGER)
    private List<Seat> seats;
}
