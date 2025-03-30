package fr.charly.businessCase.controller_api;


import com.fasterxml.jackson.annotation.JsonView;
import fr.charly.businessCase.DTO.ChargingStationDTO;
import fr.charly.businessCase.DTO.ChargingStationUpdateDTO;
import fr.charly.businessCase.entity.ChargingStation;
import fr.charly.businessCase.json_views.JsonViews;
import fr.charly.businessCase.service.ChargingStationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/chargingstation")
@AllArgsConstructor
public class ChargingStationRestController {

    private ChargingStationService chargingStationService;

    @GetMapping
    public List<ChargingStation> list() {
        return chargingStationService.list();

    }

    @GetMapping("/{uuid}")
    @JsonView(JsonViews.ChargingStationListView.class)
    public ChargingStation show(@PathVariable String uuid) {
        return chargingStationService.findOneById(uuid);
    }


    @DeleteMapping("/{uuid}")
    public Boolean delete(@PathVariable String uuid) {
        return chargingStationService.delete(uuid);
    }

    @PostMapping
    @JsonView(JsonViews.ChargingStationShowView.class)
    public ChargingStation create(@Valid @RequestBody ChargingStationDTO dto, Principal principal) {
        return chargingStationService.create(dto, principal);
    }


    @PutMapping("/{uuid}")
    public ChargingStation update(@Valid @RequestBody ChargingStationUpdateDTO dto, @PathVariable String uuid, Principal principal ){
        return chargingStationService.update(dto, uuid, principal);
    }

}
