package fr.charly.businessCase.repository;

import fr.charly.businessCase.entity.Power;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PowerRepository extends JpaRepository<Power, Long> {
}