package fr.charly.businessCase.repository;

import fr.charly.businessCase.entity.ChargingStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ChargingStationRepository extends JpaRepository<ChargingStation, String> {
}