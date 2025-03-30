package fr.charly.businessCase.service;


import fr.charly.businessCase.DTO.PowerDTO;
import fr.charly.businessCase.entity.Power;
import fr.charly.businessCase.repository.PowerRepository;
import fr.charly.businessCase.service.interfaces.ServiceListInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PowerService implements ServiceListInterface<Power, Long, PowerDTO, PowerDTO> {


    private PowerRepository powerRepository;

    @Override
    public List<Power> list() {
        return powerRepository.findAll();
    }

    @Override
    public Power create(PowerDTO o) {
        Power power = new Power();
        power.setValue(o.getValue());
        return powerRepository.saveAndFlush(power);
    }

    @Override
    public Power update(PowerDTO o, Long id) {
        Power power = findOneById(id);
        power.setValue(o.getValue());
        return powerRepository.saveAndFlush(power);
    }

    @Override
    public Boolean delete(Long id) {
        try {
            powerRepository.deleteById(id);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public Power findOneById(Long id) {
        return powerRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }
}
