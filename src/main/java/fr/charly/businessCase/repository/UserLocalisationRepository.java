package fr.charly.businessCase.repository;

import fr.charly.businessCase.entity.User;
import fr.charly.businessCase.entity.UserLocalisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserLocalisationRepository extends JpaRepository<UserLocalisation, Long> {

    Optional<UserLocalisation> findByOwnerAndLocalisationId(User owner, Long localisation_id);

}