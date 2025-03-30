package fr.charly.businessCase.controller_api;


import fr.charly.businessCase.entity.embedded.UserChargingStationId;
import fr.charly.businessCase.service.FavoriteService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/favorite")
public class FavoriteRestController {

    private FavoriteService favoriteService;

    @PostMapping
    public Boolean handleFavorite(@RequestBody UserChargingStationId data) {
        return favoriteService.handleFavorite(data);
    }


}
