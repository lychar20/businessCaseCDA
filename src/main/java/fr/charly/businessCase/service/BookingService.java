package fr.charly.businessCase.service;

import fr.charly.businessCase.DTO.BookingDTO;
import fr.charly.businessCase.DTO.ChargingStationDTO;
import fr.charly.businessCase.DTO.UserLocalisationDTO;
import fr.charly.businessCase.entity.*;
import fr.charly.businessCase.repository.BookingRepository;
import fr.charly.businessCase.service.interfaces.ServiceListInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class BookingService  {


    private BookingRepository bookingRepository;
    private ChargingStationService chargingStationService;
    private UserService userService;
    private UserLocalisationService userLocalisationService;


    public List<Booking> list() {
        return bookingRepository.findAll();
    }


    public Booking create(String chargingStationId, BookingDTO o, Long localisationId, Principal principal) {
        Booking booking = new Booking();

        ChargingStation chargingStation = chargingStationService.findOneById(chargingStationId);
        booking.setChargingStation(chargingStation);

        User user = userService.findOneByEmail(principal.getName());
        booking.setUser(user);

        UserLocalisation userLocalisation = userLocalisationService.findOneById(localisationId);
        if (userLocalisation == null) {
            throw new EntityNotFoundException("User localisation not found for ID: " + localisationId);
        }
        booking.setUserLocalisation(userLocalisation);


       // booking.setUserLocalisation(userLocalisationService.findOneById(o.getUserLocalisationId()));

        booking.setStartedAt(o.getStartedAt());
        booking.setFinishedAt(o.getFinishedAt());
        booking.setCreatedAt(LocalDateTime.now());
        booking.setStatus("En Attente");


        return bookingRepository.saveAndFlush(booking);
    }




    public Booking update(BookingDTO o, String uuid, Principal principal) throws Exception {

        Booking booking = findOneById(uuid);

        User bookingUser = booking.getUser();

        User currentUser = userService.findOneByEmail(principal.getName());

        if (bookingUser.getUuid().equals(currentUser.getUuid())) {
            booking.setStartedAt(o.getStartedAt());
            booking.setFinishedAt(o.getFinishedAt());
            booking.setStatus("En Attente");

        } else {
            throw new Exception ("Vous n'êtes pas autorisé à modifier cette réservation.");
        }
        return bookingRepository.saveAndFlush(booking);
    }


    public Boolean delete(String uuid) {
        try {
            bookingRepository.deleteById(uuid);
            return true;
        } catch (Exception e){
            return false;
        }
    }


    public Booking findOneById(String uuid) {
        return bookingRepository.findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException("booking not found"));
    }
}
