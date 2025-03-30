package fr.charly.businessCase.service;


import fr.charly.businessCase.DTO.UserLocalisationDTO;
import fr.charly.businessCase.entity.UserLocalisation;
import fr.charly.businessCase.repository.UserLocalisationRepository;
import fr.charly.businessCase.service.interfaces.ServiceInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserLocalisationService implements ServiceInterface <UserLocalisation, Long, UserLocalisationDTO, UserLocalisationDTO> {

    private UserLocalisationRepository userLocalisationRepository;
    private LocalisationService localisationService;

    @Override
    public UserLocalisation create(UserLocalisationDTO o) {
        UserLocalisation userLocalisation = new UserLocalisation();
        userLocalisation.setLocalisation(localisationService.findOneById(o.getLocalisationId()));
        userLocalisation.setIsBilling(o.getIsBilling());
        return userLocalisationRepository.saveAndFlush(userLocalisation);
    }

    @Override
    public UserLocalisation update(UserLocalisationDTO o, Long id) {
        UserLocalisation userLocalisation = findOneById(id);
        userLocalisation.setIsBilling(o.getIsBilling());
        return userLocalisationRepository.saveAndFlush(userLocalisation);
    }

    @Override
    public Boolean delete(Long id) {
        try {
            userLocalisationRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public UserLocalisation findOneById(Long id) {
        return userLocalisationRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }
}
