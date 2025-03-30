package fr.charly.businessCase.controller_api;


import com.fasterxml.jackson.annotation.JsonView;
import fr.charly.businessCase.DTO.ReviewDTO;
import fr.charly.businessCase.DTO.ReviewUpdateDTO;
import fr.charly.businessCase.DTO.UserReviewDTO;
import fr.charly.businessCase.entity.Review;
import fr.charly.businessCase.entity.UserReview;
import fr.charly.businessCase.json_views.JsonViews;
import fr.charly.businessCase.service.UserReviewService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/userReview")
@AllArgsConstructor
public class UserReviewRestController {

    private UserReviewService userReviewService;


    @GetMapping
    @JsonView(JsonViews.UserReviewMinimalView.class)
    public List<UserReview> list() {
        return userReviewService.list();
    }


    @GetMapping("/{id}")
    @JsonView(JsonViews.UserReviewMinimalView.class)
    public UserReview show(@PathVariable Long id) {
        return userReviewService.findOneById(id);
    }


    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Long id) {
        return userReviewService.delete(id);
    }

    @PostMapping("/{userReviewId}")
    @JsonView(JsonViews.UserReviewMinimalView.class)
    public UserReview create(@Valid @RequestBody UserReviewDTO dto, @PathVariable String userReviewId, Principal principal) {
        return userReviewService.create(userReviewId, dto, principal);
    }

    @PutMapping("/{id}")
    @JsonView(JsonViews.UserReviewMinimalView.class)
    public UserReview update(@Valid @RequestBody UserReviewDTO dto, @PathVariable Long id, Principal principal ) throws Exception {
        return userReviewService.update(dto, id, principal);
    }


}
