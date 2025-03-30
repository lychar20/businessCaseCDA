package fr.charly.businessCase.controller_api;

import com.fasterxml.jackson.annotation.JsonView;
import fr.charly.businessCase.DTO.BookingDTO;
import fr.charly.businessCase.entity.Booking;
import fr.charly.businessCase.json_views.JsonViews;
import fr.charly.businessCase.service.BookingService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/booking")
@AllArgsConstructor
public class BookingRestController {

    private BookingService bookingService;


    @GetMapping
    @JsonView(JsonViews.BookingMinimalView.class)
    public List<Booking> list() {
        return bookingService.list();
    }


    @GetMapping("/{id}")
    @JsonView(JsonViews.BookingMinimalView.class)
    public Booking show(@PathVariable String id) {
        return bookingService.findOneById(id);
    }


    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable String id) {
        return bookingService.delete(id);
    }

    @PostMapping("/{chargingStationId}")
    @JsonView(JsonViews.BookingMinimalView.class)
    public Booking create(@Valid @RequestBody BookingDTO dto, @PathVariable String chargingStationId, @RequestParam Long localisationId, Principal principal) {
        return bookingService.create(chargingStationId, dto, localisationId, principal);
    }

    @PutMapping("/{id}")
    @JsonView(JsonViews.BookingMinimalView.class)
    public Booking update(@Valid @RequestBody BookingDTO dto, @PathVariable String id, Principal principal ) throws Exception {
        return bookingService.update(dto, id, principal);
    }


}
