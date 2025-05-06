package com.example.mtb.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
public class User extends UserDetails{

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Feedback> feedbackList;
}
