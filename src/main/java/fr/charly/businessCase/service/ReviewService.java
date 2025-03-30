package fr.charly.businessCase.service;


import fr.charly.businessCase.DTO.ChargingStationDTO;
import fr.charly.businessCase.DTO.ReviewDTO;
import fr.charly.businessCase.DTO.ReviewUpdateDTO;
import fr.charly.businessCase.entity.ChargingStation;
import fr.charly.businessCase.entity.Review;
import fr.charly.businessCase.entity.User;
import fr.charly.businessCase.repository.ReviewRepository;
import fr.charly.businessCase.service.interfaces.ServiceListInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ReviewService  {

    private ReviewRepository reviewRepository;

    private UserService userService;
    private ChargingStationService chargingStationService;



    public List<Review> list() {
        return reviewRepository.findAll();
    }


    public Review create(String chargingStationId, ReviewDTO o, Principal principal) {
        Review review = new Review();
       // review.setChargingStation((ChargingStation) chargingStationService.findOneById(chargingStationId));
       // review.setUser((User) userService.findOneById(userId));


       ChargingStation chargingStation = chargingStationService.findOneById(chargingStationId);
       review.setChargingStation(chargingStation);

        User user = userService.findOneByEmail(principal.getName());
        review.setUser(user);

        review.setRating(o.getRating());
        review.setContent(o.getContent());
        review.setCreatedAt(LocalDateTime.now());

        return reviewRepository.saveAndFlush(review);
    }




    public Review update(ReviewUpdateDTO o, Long reviewId, Principal principal) throws Exception {
        Review review = findOneById(reviewId);

        User reviewUser = review.getUser();

        User currentUser = userService.findOneByEmail(principal.getName());

        if (reviewUser.getUuid().equals(currentUser.getUuid())) {
            review.setContent(o.getContent());
            review.setRating(o.getRating());
            review.setUpdateAt(LocalDateTime.now());
        } else {
            throw new Exception ("Vous n'êtes pas autorisé à modifier cette critique.");
        }
        return reviewRepository.saveAndFlush(review);
    }


    public Boolean delete(Long id) {
        try {
            reviewRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public Review findOneById(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }
}
