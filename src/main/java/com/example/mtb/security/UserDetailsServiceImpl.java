package com.example.mtb.security;

import com.example.mtb.repository.UserDetailsRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDetailsRepository userDetailsRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<com.example.mtb.entity.UserDetails> ouserDetails= userDetailsRepository.findByEmail(username);
        com.example.mtb.entity.UserDetails userDetails=ouserDetails.get();


            return User.builder()
                    .username(userDetails.getEmail())
                    .password(userDetails.getPassword())
                    .authorities(userDetails.getUserRole().name())
                    .build();



    }
}
