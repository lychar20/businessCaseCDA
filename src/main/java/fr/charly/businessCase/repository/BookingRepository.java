package fr.charly.businessCase.repository;

import fr.charly.businessCase.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookingRepository extends JpaRepository<Booking, String> {
}