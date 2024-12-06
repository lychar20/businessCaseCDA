package fr.charly.businessCase.repository;

import fr.charly.businessCase.entity.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {
}