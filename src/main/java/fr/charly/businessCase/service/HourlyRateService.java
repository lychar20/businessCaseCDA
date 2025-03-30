package fr.charly.businessCase.service;


import fr.charly.businessCase.DTO.HourlyRateDTO;
import fr.charly.businessCase.entity.HourlyRate;
import fr.charly.businessCase.repository.HourlyRateRepository;
import fr.charly.businessCase.service.interfaces.ServiceInterface;
import fr.charly.businessCase.service.interfaces.ServiceListInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class HourlyRateService implements ServiceListInterface<HourlyRate, Long, HourlyRateDTO, HourlyRateDTO> {

    private HourlyRateRepository hourlyRateRepository;


    @Override
    public List<HourlyRate> list() {
        return hourlyRateRepository.findAll();
    }

    @Override
    public HourlyRate create(HourlyRateDTO o) {
        HourlyRate hourlyRate = new HourlyRate();
        hourlyRate.setValue(o.getValue());
        hourlyRate.setMinimumDuration(o.getMinimumDuration());
        //hourlyRate.setChargingStation(chargingStationService.findOneById(o.getChargingStationId()));
        return hourlyRateRepository.saveAndFlush(hourlyRate);
    }

    @Override
    public HourlyRate update(HourlyRateDTO o, Long id) {
        HourlyRate hourlyRate = findOneById(id);
        hourlyRate.setValue(o.getValue());
        hourlyRate.setMinimumDuration(o.getMinimumDuration());
     //   hourlyRate.setChargingStation(chargingStationService.findOneById(o.getChargingStationId()));
        return hourlyRateRepository.saveAndFlush(hourlyRate);
    }

    @Override
    public Boolean delete(Long id) {
        try {
            hourlyRateRepository.deleteById(id);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public HourlyRate findOneById(Long id) {
        return hourlyRateRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }


}
