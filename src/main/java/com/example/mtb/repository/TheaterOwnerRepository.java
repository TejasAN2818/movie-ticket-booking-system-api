package com.example.mtb.repository;

import com.example.mtb.entity.TheaterOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterOwnerRepository extends JpaRepository<TheaterOwner, String> {

}
