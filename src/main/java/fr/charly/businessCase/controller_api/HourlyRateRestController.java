package fr.charly.businessCase.controller_api;


import com.fasterxml.jackson.annotation.JsonView;
import fr.charly.businessCase.DTO.HourlyRateDTO;
import fr.charly.businessCase.entity.HourlyRate;
import fr.charly.businessCase.json_views.JsonViews;
import fr.charly.businessCase.service.HourlyRateService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/hourlyRate")
public class HourlyRateRestController {

    private HourlyRateService hourlyRateService;

    @GetMapping
    @JsonView(JsonViews.HourlyRateMinimalView.class)
    public List<HourlyRate> list() {
        return hourlyRateService.list();
    }

    @GetMapping("/{id}")
    @JsonView(JsonViews.HourlyRateMinimalView.class)
    public HourlyRate show(@PathVariable Long id) {
        return hourlyRateService.findOneById(id);
    }


    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Long id) {
        return hourlyRateService.delete(id);
    }

    @PostMapping
    @JsonView(JsonViews.HourlyRateMinimalView.class)
    public HourlyRate create(@Valid @RequestBody HourlyRateDTO dto) {
        return hourlyRateService.create(dto);
    }

    @PutMapping("/{id}")
    @JsonView(JsonViews.HourlyRateMinimalView.class)
    public HourlyRate update(@Valid @RequestBody HourlyRateDTO dto, @PathVariable Long id ){
        return hourlyRateService.update(dto, id);
    }

}
