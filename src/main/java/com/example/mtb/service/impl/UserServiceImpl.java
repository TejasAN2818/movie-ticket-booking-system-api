package com.example.mtb.service.impl;

import com.example.mtb.entity.TheaterOwner;
import com.example.mtb.entity.User;
import com.example.mtb.entity.UserDetails;
import com.example.mtb.enums.UserRole;
import com.example.mtb.excaption.UserRegistrationexcaption;
import com.example.mtb.repository.TheaterOwnerRepository;
import com.example.mtb.repository.UserDetailsRepository;
import com.example.mtb.repository.UserRepository;
import com.example.mtb.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserDetailsRepository userDetailsRepository;

    private final UserRepository userRepository;

    private final TheaterOwnerRepository theaterOwnerRepository;


    @Override
    public UserDetails userRegister(UserDetails user) {
        if(userDetailsRepository.existsByEmail(user.getEmail())){
            throw new UserRegistrationexcaption("this email already existed");
        }else{

            if (user.getUserRole() == UserRole.USER) {
                // Create User object and save
                User newUser = new User();
                newUser.setUsername(user.getUsername());
                newUser.setEmail(user.getEmail());
                newUser.setPassword(user.getPassword());
                newUser.setUserRole(user.getUserRole());
                newUser.setDateOfBirth(user.getDateOfBirth());
                newUser.setCreatedAt(System.currentTimeMillis());
                newUser.setUpdatedAt(System.currentTimeMillis());
                return userRepository.save(newUser);
            } else if (user.getUserRole() == UserRole.THEATER_OWNER) {
                // Create TheaterOwner object and save
                TheaterOwner theaterOwner = new TheaterOwner();
                theaterOwner.setUsername(user.getUsername());
                theaterOwner.setEmail(user.getEmail());
                theaterOwner.setPassword(user.getPassword());
                theaterOwner.setUserRole(user.getUserRole());
                theaterOwner.setDateOfBirth(user.getDateOfBirth());
                theaterOwner.setCreatedAt(System.currentTimeMillis());
                theaterOwner.setUpdatedAt(System.currentTimeMillis());
                return theaterOwnerRepository.save(theaterOwner);
            } else {
                throw new IllegalArgumentException("Invalid role");
            }
        }
    }



}
