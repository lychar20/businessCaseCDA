package fr.charly.businessCase.service;


import fr.charly.businessCase.entity.Favorite;
import fr.charly.businessCase.entity.embedded.UserChargingStationId;
import fr.charly.businessCase.repository.FavoriteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FavoriteService {

    private FavoriteRepository favoriteRepository;

    public Boolean handleFavorite(UserChargingStationId o) {
        Optional<Favorite> optionalFavorite = favoriteRepository.findById(o);
        if (optionalFavorite.isEmpty()) {
            Favorite favorite = new Favorite();
            favorite.setId(o);
            favorite.setCreatedAt(LocalDateTime.now());
            favoriteRepository.saveAndFlush(favorite);
            return true;
        }
        favoriteRepository.delete(optionalFavorite.get());
        return false;
    }

}
