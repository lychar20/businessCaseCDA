package fr.charly.businessCase.service;


import fr.charly.businessCase.DTO.ChargingStationDTO;
import fr.charly.businessCase.DTO.ChargingStationUpdateDTO;
import fr.charly.businessCase.DTO.HourlyRateDTO;
import fr.charly.businessCase.entity.ChargingStation;
import fr.charly.businessCase.entity.HourlyRate;
import fr.charly.businessCase.entity.Localisation;
import fr.charly.businessCase.entity.User;
import fr.charly.businessCase.repository.ChargingStationRepository;
import fr.charly.businessCase.service.interfaces.ServiceInterface;
import fr.charly.businessCase.service.interfaces.ServiceListInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ChargingStationService  {

    HourlyRateService hourlyRateService;
    ChargingStationRepository chargingStationRepository;
    LocalisationService localisationService;
    PowerService powerService;
    UserService userService;


    public List<ChargingStation> list() {
        return chargingStationRepository.findAll();
    }


    public ChargingStation create(ChargingStationDTO o, Principal principal) {
        ChargingStation chargingStation = new ChargingStation();
        chargingStation.setName(o.getName());

        Localisation localisation;
        Long localisationId = o.getLocalisation().getId();
        if (localisationId != 0) { // Address already exists
            localisation = localisationService.findOneById(localisationId);
        } else {
            localisation = localisationService.create(o.getLocalisation(), principal);
        }
        chargingStation.setLocalisation(localisation);

        chargingStation.setPower(powerService.findOneById(o.getPowerId()));
        chargingStation.setAccessDirectives(o.getAccessDirectives());
        chargingStation.setOnFoot(o.getOnFoot());

        List<HourlyRate> hourlyRatesList = new ArrayList<>();
        for (HourlyRateDTO hourlyRateDTO : o.getHourlyRates()) {
            HourlyRate hourlyRate;
            Long hourlyRateId = hourlyRateDTO.getId();
            if (hourlyRateId != 0) { // Hourly already exists
                hourlyRate = hourlyRateService.findOneById(hourlyRateId);
            } else {
                hourlyRate = hourlyRateService.create(hourlyRateDTO);
            }

            User user = userService.findOneByEmail(principal.getName());
            chargingStation.getLocalisation().setOwner(user);

            hourlyRate.setChargingStation(chargingStation);
            hourlyRatesList.add(hourlyRate);
        }
        chargingStation.setHourlyRates(hourlyRatesList);
        //chargingStation.setMedias(o.getMedias());
        chargingStation.setCreatedAt(LocalDateTime.now());
        return chargingStationRepository.saveAndFlush(chargingStation); //chargingStation;
    }

//    public ChargingStation create(ChargingStationDTO chargingStationDTO, Principal principal) {
//
//        ChargingStation chargingStation = create(chargingStationDTO);
//        User user = userService.findOneByEmail(principal.getName());
//        chargingStation.getLocalisation().setOwner(user);
//        return chargingStationRepository.saveAndFlush(chargingStation);
//    }


    public ChargingStation update(ChargingStationUpdateDTO o, String id, Principal principal) {
        ChargingStation chargingStation = findOneById(id);
        chargingStation.setName(o.getName());
        chargingStation.setPower(powerService.findOneById(o.getPowerId()));
        chargingStation.setAccessDirectives(o.getAccessDirectives());
        chargingStation.setOnFoot(o.getOnFoot());


        List<HourlyRate> hourlyRatesList = new ArrayList<>();
        for (HourlyRateDTO hourlyRateDTO : o.getHourlyRates()) {
            HourlyRate hourlyRate;
            Long hourlyRateId = hourlyRateDTO.getId();
            if (hourlyRateId != null) { // Hourly already exists
                hourlyRate = hourlyRateService.findOneById(hourlyRateId);
            } else {
                hourlyRate = hourlyRateService.create(hourlyRateDTO);
            }
            hourlyRate.setChargingStation(chargingStation);
            hourlyRatesList.add(hourlyRate);
        }
        //chargingStation.setHourlyRates(o.getHourlyRates());
        chargingStation.setHourlyRates(hourlyRatesList);


        //chargingStation.setMedias(o.getMedias());
        chargingStation.setUpdatedAt(LocalDateTime.now());

        Localisation localisation;
        Long localisationId = o.getLocalisation().getId();
        if (localisationId != null) {
            localisation = localisationService.findOneById(localisationId);
        } else {
            localisation = localisationService.create(o.getLocalisation(), principal);
        }
        chargingStation.setLocalisation(localisation);
        return chargingStationRepository.saveAndFlush(chargingStation);
    }


    public Boolean delete(String uuid) {
        try {
            ChargingStation chargingStation = findOneById(uuid);
            chargingStation.setName(null);
            chargingStation.setLocalisation(null);
            chargingStation.setPower(null);
            chargingStation.setAccessDirectives(null);
            chargingStation.setOnFoot(null);
            chargingStation.setHourlyRates(null);
            chargingStation.setMedias(null);
            chargingStationRepository.saveAndFlush(chargingStation);
            return true;
        } catch (Exception e) {
            return false;
        }

    }


    public ChargingStation findOneById(String uuid) {
        return chargingStationRepository.findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Charging Station not found"));
    }
}
