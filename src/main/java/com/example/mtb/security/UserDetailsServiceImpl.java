package com.example.mtb.security;

import com.example.mtb.entity.User;
import com.example.mtb.repository.UserDetailsRepository;
import com.example.mtb.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static com.example.mtb.entity.User.*;

@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDetailsRepository userDetailsRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<com.example.mtb.entity.UserDetails> optionalUserDetails=userDetailsRepository.findByEmail(username);

        if (optionalUserDetails.isEmpty()){
            throw new UsernameNotFoundException("user not found by this Id");
        }else{
            com.example.mtb.entity.UserDetails user=optionalUserDetails.get();

            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getEmail())
                    .password(user.getPassword())
                    .authorities(user.getUserRole().name())
                    .build();


        }
    }

}
