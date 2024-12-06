package fr.charly.businessCase.repository;

import fr.charly.businessCase.entity.Localisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LocalisationRepository extends JpaRepository<Localisation, Long> {
}