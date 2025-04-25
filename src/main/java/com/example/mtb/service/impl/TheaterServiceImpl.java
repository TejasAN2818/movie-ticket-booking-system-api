package com.example.mtb.service.impl;

import com.example.mtb.dto.theater_dto.TheaterRegisterationRequest;
import com.example.mtb.dto.theater_dto.TheaterRequest;
import com.example.mtb.dto.theater_dto.TheaterResponse;
import com.example.mtb.entity.Theater;
import com.example.mtb.entity.TheaterOwner;
import com.example.mtb.entity.UserDetails;
import com.example.mtb.enums.UserRole;
import com.example.mtb.excaption.TheaterNotFoundByIdExcaption;
import com.example.mtb.excaption.UserNotFoundByEmailExcaption;
import com.example.mtb.repository.TheaterOwnerRepository;
import com.example.mtb.repository.TheaterRepository;
import com.example.mtb.repository.UserDetailsRepository;
import com.example.mtb.service.TheaterService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TheaterServiceImpl implements TheaterService {

    private final TheaterRepository theaterRepository;
    private final UserDetailsRepository userDetailsRepository;
    private final TheaterOwnerRepository theaterOwnerRepository;


    @Override
    public TheaterResponse registerTheater(TheaterRegisterationRequest theater, String userEmail) {

        Optional<UserDetails> optionalUserDetails=userDetailsRepository.findByEmail(userEmail);
        if (optionalUserDetails.isEmpty()){
            throw new UserNotFoundByEmailExcaption("user not found in this email");
        }else{
            UserDetails userDetails=optionalUserDetails.get();
            if (userDetails.getUserRole() != UserRole.THEATER_OWNER){
                throw new UserNotFoundByEmailExcaption("this user not theater owner");
            }else{
                Theater newTheater = new Theater();
                newTheater.setName(theater.name());
                newTheater.setAddress(theater.address());
                newTheater.setCity(theater.city());
                newTheater.setLandmark(theater.landmark());
                newTheater.setCreatedAt(System.currentTimeMillis());
              //  newTheater.setCreatedBy(userDetails.getUserId());

                newTheater.setTheaterOwner((TheaterOwner) userDetails);

                List<Theater> theaterList=new ArrayList<Theater>();
                theaterList.add(newTheater);

                TheaterOwner theaterOwner=new TheaterOwner();
                theaterOwner.setTheaters(theaterList);




                theaterOwnerRepository.save(theaterOwner);
                theaterRepository.save(newTheater);
                return new TheaterResponse(
                        newTheater.getName(),
                        newTheater.getAddress(),
                        newTheater.getCity(),
                        newTheater.getLandmark()
                );


            }
        }
    }

    @Override
    public String updateTheaterById(TheaterRequest updatedTheater, String theaterId) {

        Optional<Theater> optionalTheater=theaterRepository.findById(theaterId);
        if(optionalTheater.isEmpty()){
            throw new TheaterNotFoundByIdExcaption("Theater not found by this theaterId!");
        }else{
            Theater existingTheater=optionalTheater.get();
            existingTheater.setName(updatedTheater.name());
            existingTheater.setAddress(updatedTheater.address());
            existingTheater.setCity(updatedTheater.city());
            existingTheater.setLandmark(updatedTheater.landmark());
            existingTheater.setUpdatedAt(System.currentTimeMillis());
            theaterRepository.save(existingTheater);
            return "theater updated sucessfully";
        }
    }

    @Override
    public TheaterResponse findTheaterById(String theaterId) {
        Optional<Theater> optionalTheater=theaterRepository.findById(theaterId);
        if (optionalTheater.isEmpty()){
            throw new TheaterNotFoundByIdExcaption("theater not found By this Id");
        }else{
            Theater theater=optionalTheater.get();
            return new TheaterResponse(
                    theater.getName(),
                    theater.getAddress(),
                    theater.getCity(),
                    theater.getLandmark()
                    );
        }
    }
}
