package com.example.mtb.service.impl;

import com.example.mtb.dto.UserRegistrationResuest;
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
    public UserDetails userRegister(UserRegistrationResuest user) {
        if(userDetailsRepository.existsByEmail(user.email())){
            throw new UserRegistrationexcaption("this email already existed");
        }else{

            if (user.userRole() == UserRole.USER) {
                User newUser = new User();
                newUser.setUsername(user.username());
                newUser.setEmail(user.email());
                newUser.setPassword(user.password());
                newUser.setUserRole(user.userRole());
                newUser.setPhoneNumber(user.phoneNumber());
                newUser.setDateOfBirth(user.dateOfBirth());


                return userRepository.save(newUser);





            } else if (user.userRole() == UserRole.THEATER_OWNER) {

                TheaterOwner theaterOwner = new TheaterOwner();
                theaterOwner.setUsername(user.username());
                theaterOwner.setEmail(user.email());
                theaterOwner.setPassword(user.password());
                theaterOwner.setUserRole(user.userRole());
                theaterOwner.setPhoneNumber(user.phoneNumber());
                theaterOwner.setDateOfBirth(user.dateOfBirth());
                return theaterOwnerRepository.save(theaterOwner);
            } else {
                throw new IllegalArgumentException("Invalid role");
            }
        }
    }



}
