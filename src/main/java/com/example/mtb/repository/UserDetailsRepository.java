package com.example.mtb.repository;


import com.example.mtb.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, String> {

    boolean existsByEmail(String email);

    Optional<UserDetails> findByEmail(String email);
}
