package com.example.mtb.service.impl;

import com.example.mtb.dto.feedback_dto.FeedbackRequest;
import com.example.mtb.dto.feedback_dto.FeedbackResponse;
import com.example.mtb.entity.Feedback;
import com.example.mtb.entity.Movie;
import com.example.mtb.entity.User;
import com.example.mtb.excaption.MovieNotFoundByIdExcaption;
import com.example.mtb.excaption.UserNotFoundByIdExcaption;
import com.example.mtb.excaption.UserRegistrationexcaption;
import com.example.mtb.repository.FeedbackRepository;
import com.example.mtb.repository.MovieRepository;
import com.example.mtb.repository.UserRepository;
import com.example.mtb.service.FeedbackService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final MovieRepository movieRepository;
    private UserRepository userRepository;


    @Override
    public FeedbackResponse createFeedBack(FeedbackRequest feedback, String userId, String movieId) {

        Optional<User> optionalUser=userRepository.findById(userId);
        if(optionalUser.isPresent()){
            Optional<Movie> optionalMovie=movieRepository.findById(movieId);
            if (optionalMovie.isPresent()){


                User user=optionalUser.get();
                Movie movie=optionalMovie.get();

                Feedback newFeedback=new Feedback();
                newFeedback.setRatings(feedback.ratings());
                newFeedback.setReview(feedback.review());
                newFeedback.setCreatedAt(System.currentTimeMillis());
                newFeedback.setUser(user);
                newFeedback.setMovie(movie);

                List<Feedback> feedbackList=new ArrayList<>();
                feedbackList.add(newFeedback);

                movie.setFeedbackList(feedbackList);
                user.setFeedbackList(feedbackList);

                movieRepository.save(movie);
                userRepository.save(user);

                Feedback savedFeedback=feedbackRepository.save(newFeedback);

//                Movie updatedMovie=movieRepository.findById(movieId).get();
//
//                List<Feedback> newFeadFeedbackList=updatedMovie.getFeedbackList();
//                int noOfFeadback = 0;
//                int totalRatings=0;
//                for (Feedback ratingsListFeadBack : newFeadFeedbackList){
//                    noOfFeadback++;
//                    totalRatings=totalRatings+ratingsListFeadBack.getRatings();
//                }
//                System.out.println("no of feadback    "+noOfFeadback);
//                System.out.println("total ratings    "+totalRatings);
//
//
//                double avgRatings;
//                if (noOfFeadback==0){
//                    avgRatings=0;
//                }else {
//                   avgRatings= totalRatings/noOfFeadback;
//                }


                return new FeedbackResponse(
                        savedFeedback.getRatings(),
                        savedFeedback.getReview()

                );

            }else{
                throw new MovieNotFoundByIdExcaption("movie not found by this Id");
            }
        }else {
            throw new UserNotFoundByIdExcaption("User not found by Id Excaption");
        }
    }
}
