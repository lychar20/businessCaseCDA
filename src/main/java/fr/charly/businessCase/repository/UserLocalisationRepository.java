package fr.charly.businessCase.repository;

import fr.charly.businessCase.entity.UserLocalisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserLocalisationRepository extends JpaRepository<UserLocalisation, Long> {
}