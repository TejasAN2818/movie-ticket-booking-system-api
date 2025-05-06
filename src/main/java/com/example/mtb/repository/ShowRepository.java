package com.example.mtb.repository;

import com.example.mtb.dto.show_dto.AllShowResponse;
import com.example.mtb.entity.Screen;
import com.example.mtb.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowRepository extends JpaRepository<Show, String> {

}
