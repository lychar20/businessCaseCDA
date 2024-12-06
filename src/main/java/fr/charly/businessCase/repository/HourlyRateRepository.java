package fr.charly.businessCase.repository;

import fr.charly.businessCase.entity.HourlyRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HourlyRateRepository extends JpaRepository<HourlyRate, Long> {
}