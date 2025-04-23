package com.example.mtb.service;


import com.example.mtb.dto.UserRegistrationResuest;
import com.example.mtb.dto.UserRequest;
import com.example.mtb.dto.UserResponse;
import com.example.mtb.entity.UserDetails;


public interface UserService {

    public UserResponse userRegister(UserRegistrationResuest user);


    String updateUser(UserRequest updatedUser, String email);

    String softDelete(String email);
}
