package fr.charly.businessCase.service;



import fr.charly.businessCase.DTO.UserReviewDTO;
import fr.charly.businessCase.entity.User;
import fr.charly.businessCase.entity.UserReview;
import fr.charly.businessCase.repository.UserReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class UserReviewService {

    private UserReviewRepository userReviewRepository;
    private UserService userService;

    public List<UserReview> list() {
        return userReviewRepository.findAll();
    }


    public UserReview create(String userReviewId, UserReviewDTO o, Principal principal) {
        UserReview userReview = new UserReview();

        User user = userService.findOneById(userReviewId);
        userReview.setUserTo(user);

        User userFrom = userService.findOneByEmail(principal.getName());
        userReview.setUserFrom(userFrom);

        userReview.setRating(o.getRating());
        userReview.setContent(o.getContent());
        userReview.setCreatedAt(LocalDateTime.now());

        return userReviewRepository.saveAndFlush(userReview);
    }




    public UserReview update(UserReviewDTO o, Long userReviewId, Principal principal) throws Exception {
        UserReview userReview = findOneById(userReviewId);

        User reviewUser = userReview.getUserFrom();

        User currentUser = userService.findOneByEmail(principal.getName());

        if (reviewUser.getUuid().equals(currentUser.getUuid())) {
            userReview.setContent(o.getContent());
            userReview.setRating(o.getRating());
            userReview.setUpdatedAt(LocalDateTime.now());
        } else {
            throw new Exception ("Vous n'êtes pas autorisé à modifier cette critique.");
        }
        return userReviewRepository.saveAndFlush(userReview);
    }


    public Boolean delete(Long id) {
        try {
            userReviewRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public UserReview findOneById(Long id) {
        return userReviewRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

}
