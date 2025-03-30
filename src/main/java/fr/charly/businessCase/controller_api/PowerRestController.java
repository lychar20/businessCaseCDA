package fr.charly.businessCase.controller_api;


import com.fasterxml.jackson.annotation.JsonView;
import fr.charly.businessCase.DTO.PowerDTO;
import fr.charly.businessCase.entity.Power;
import fr.charly.businessCase.json_views.JsonViews;
import fr.charly.businessCase.service.PowerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/power")
@AllArgsConstructor
public class PowerRestController {

    private PowerService powerService;


    @GetMapping("/{id}")
    @JsonView(JsonViews.PowerMinimalView.class)
    public Power show(@PathVariable Long id) {
        return powerService.findOneById(id);
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Long id) {
        return powerService.delete(id);
    }


    @PostMapping
    @JsonView(JsonViews.PowerMinimalView.class)
    public Power create(@Valid @RequestBody PowerDTO dto) {
        return powerService.create(dto);
    }


    @PutMapping("/{id}")
    @JsonView(JsonViews.PowerMinimalView.class)
    public Power update(@Valid @RequestBody PowerDTO dto, @PathVariable Long id ){
        return powerService.update(dto, id);
    }

}
