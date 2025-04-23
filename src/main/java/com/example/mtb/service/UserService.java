package com.example.mtb.service;


import com.example.mtb.dto.UserRegistrationResuest;
import com.example.mtb.entity.UserDetails;


public interface UserService {

    public UserDetails userRegister(UserRegistrationResuest user);


}
