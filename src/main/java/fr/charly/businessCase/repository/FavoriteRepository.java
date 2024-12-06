package fr.charly.businessCase.repository;

import fr.charly.businessCase.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
}