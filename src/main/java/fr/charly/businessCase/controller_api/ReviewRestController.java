package fr.charly.businessCase.controller_api;


import com.fasterxml.jackson.annotation.JsonView;
import fr.charly.businessCase.DTO.ReviewDTO;
import fr.charly.businessCase.DTO.ReviewUpdateDTO;
import fr.charly.businessCase.entity.ChargingStation;
import fr.charly.businessCase.entity.Review;
import fr.charly.businessCase.json_views.JsonViews;
import fr.charly.businessCase.service.ReviewService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/review")
@AllArgsConstructor
public class ReviewRestController {

    private ReviewService reviewService;


    @GetMapping
    @JsonView(JsonViews.ReviewMinimalView.class)
    public List<Review> list() {
        return reviewService.list();
    }


    @GetMapping("/{id}")
    @JsonView(JsonViews.ReviewMinimalView.class)
    public Review show(@PathVariable Long id) {
        return reviewService.findOneById(id);
    }


    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Long id) {
        return reviewService.delete(id);
    }

    @PostMapping("/{chargingStationId}")
    @JsonView(JsonViews.ReviewMinimalView.class)
    public Review create(@Valid @RequestBody ReviewDTO dto, @PathVariable String chargingStationId, Principal principal) {
        return reviewService.create(chargingStationId, dto, principal);
    }

    @PutMapping("/{id}")
    @JsonView(JsonViews.ReviewMinimalView.class)
    public Review update(@Valid @RequestBody ReviewUpdateDTO dto, @PathVariable Long id, Principal principal ) throws Exception {
        return reviewService.update(dto, id, principal);
    }


}
