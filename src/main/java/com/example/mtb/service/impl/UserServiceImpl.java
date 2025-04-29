package com.example.mtb.service.impl;

import com.example.mtb.dto.UserRegistrationResuest;
import com.example.mtb.dto.UserRequest;
import com.example.mtb.dto.UserResponse;
import com.example.mtb.entity.TheaterOwner;
import com.example.mtb.entity.User;
import com.example.mtb.entity.UserDetails;
import com.example.mtb.enums.UserRole;
import com.example.mtb.excaption.UserNotFoundByEmailExcaption;
import com.example.mtb.excaption.UserRegistrationexcaption;
import com.example.mtb.repository.TheaterOwnerRepository;
import com.example.mtb.repository.UserDetailsRepository;
import com.example.mtb.repository.UserRepository;
import com.example.mtb.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserDetailsRepository userDetailsRepository;

    private final UserRepository userRepository;

    private final TheaterOwnerRepository theaterOwnerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserResponse userRegister(UserRegistrationResuest user) {
        if (userDetailsRepository.existsByEmail(user.email())) {
            throw new UserRegistrationexcaption("this email already existed");
        } else {
            if (user.userRole() == UserRole.USER) {
                User newUser = new User();
                newUser.setUsername(user.username());
                newUser.setEmail(user.email());
                newUser.setPassword(passwordEncoder.encode(user.password()));
                newUser.setUserRole(user.userRole());
                newUser.setPhoneNumber(user.phoneNumber());
                newUser.setDateOfBirth(user.dateOfBirth());
                newUser.setCreatedAt(System.currentTimeMillis());


                userRepository.save(newUser);
                return new UserResponse(
                        newUser.getUserId(),
                        newUser.getUsername(),
                        newUser.getEmail(),
                        newUser.getUserRole());


            } else if (user.userRole() == UserRole.THEATER_OWNER) {

                TheaterOwner theaterOwner = new TheaterOwner();
                theaterOwner.setUsername(user.username());
                theaterOwner.setEmail(user.email());
                theaterOwner.setPassword(passwordEncoder.encode(user.password()));
                theaterOwner.setUserRole(user.userRole());
                theaterOwner.setPhoneNumber(user.phoneNumber());
                theaterOwner.setDateOfBirth(user.dateOfBirth());
                theaterOwner.setCreatedAt(System.currentTimeMillis());
                theaterOwnerRepository.save(theaterOwner);
                return new UserResponse(
                        theaterOwner.getUserId(),
                        theaterOwner.getUsername(),
                        theaterOwner.getEmail(),
                        theaterOwner.getUserRole());


            } else {
                throw new IllegalArgumentException("Invalid role");
            }
        }
    }

    @Override
    public String updateUser(UserRequest updatedUser, String email) {
        Optional<UserDetails> opt = userDetailsRepository.findByEmail(email);
        if (opt.isEmpty()) {
            throw new UserNotFoundByEmailExcaption("user not found based on this email");
        } else {
            UserDetails user = opt.get();
            user.setUsername(updatedUser.username());
            user.setPhoneNumber(updatedUser.phoneNumber());
            user.setDateOfBirth(updatedUser.dateOfBirth());
            user.setUpdatedAt(System.currentTimeMillis());

            userDetailsRepository.save(user);
            return "update sucessfully";


        }


    }

    @Override
    public String softDelete(String email) {

       Optional<UserDetails> otpUser=userDetailsRepository.findByEmail(email);
       if (otpUser.isEmpty()){
           throw new UserNotFoundByEmailExcaption("user not found By this email");
       }else{
           UserDetails userDetails=otpUser.get();
           userDetails.setDelete(true);
           userDetails.setDeletedAt(Instant.now());
           userDetailsRepository.save(userDetails);
           return "user deleted";
       }
    }
}
