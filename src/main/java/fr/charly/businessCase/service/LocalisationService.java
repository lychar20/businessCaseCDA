package fr.charly.businessCase.service;

import fr.charly.businessCase.DTO.ChargingStationDTO;
import fr.charly.businessCase.DTO.LocalisationDTO;
import fr.charly.businessCase.DTO.UserLocalisationDTO;
import fr.charly.businessCase.entity.ChargingStation;
import fr.charly.businessCase.entity.Localisation;
import fr.charly.businessCase.entity.User;
import fr.charly.businessCase.entity.UserLocalisation;
import fr.charly.businessCase.repository.LocalisationRepository;
import fr.charly.businessCase.repository.UserLocalisationRepository;
import fr.charly.businessCase.service.interfaces.ServicePrincipalInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LocalisationService implements ServicePrincipalInterface<Localisation, Long, LocalisationDTO, LocalisationDTO> {


    private LocalisationRepository localisationRepository;
    private UserLocalisationRepository userLocalisationRepository;
    private UserService userService;


    @Override
    public List<Localisation> list() {
        return localisationRepository.findAll();
    }

    @Override
    public Localisation create(LocalisationDTO o, Principal principal) {
        Localisation localisation = new Localisation();
        localisation.setStreetNumber(o.getStreetNumber());
        localisation.setStreetName(o.getStreetName());
        localisation.setZipCode(o.getZipCode());
        localisation.setCity(o.getCity());
        localisation.setLatitude(o.getLatitude());
        localisation.setLongitude(o.getLongitude());

        User user = userService.findOneByEmail(principal.getName());
        localisation.setOwner(user);

        localisation = localisationRepository.saveAndFlush(localisation);

       // localisation.setOwner(localisation.getOwner());

        List<UserLocalisation> userLocalisationList = new ArrayList<>();
        for (UserLocalisationDTO userLocalisationDTO : o.getUserLocalisations()) {

            Optional<UserLocalisation> existingUserLocalisation = userLocalisationRepository.findByOwnerAndLocalisationId(
                    userLocalisationDTO.getOwnerId(),
                    localisation.getId()
            );

            if (existingUserLocalisation.isEmpty()) {
                UserLocalisation userLocalisation = new UserLocalisation();
                userLocalisation.setLocalisation(localisation);
                userLocalisation.setIsBilling(userLocalisationDTO.getIsBilling());

                User owner = localisation.getOwner();

                if (owner != null) {
                    userLocalisation.setOwner(owner);
                } else {
                    String rendu = ("La localisation n'a pas de propriétaire");
                }
                userLocalisationList.add(userLocalisation);

            }
        }
            userLocalisationRepository.saveAll(userLocalisationList);

            return localisation;

    }




    @Override
    public Localisation update (LocalisationDTO o, Long id, Principal principal){
            Localisation localisation = findOneById(id);
            localisation.setStreetNumber(o.getStreetNumber());
            localisation.setStreetName(o.getStreetName());
            localisation.setZipCode(o.getZipCode());
            localisation.setCity(o.getCity());
            localisation.setLatitude(o.getLatitude());
            localisation.setLongitude(o.getLongitude());

            return localisationRepository.saveAndFlush(localisation);
        }


        @Override
        public Boolean delete (Long id){
            try {
                Localisation address = findOneById(id);
                address.setStreetNumber(null);
                address.setStreetName(null);
                address.setZipCode(null);
                address.setCity(null);
                address.setLatitude(null);
                address.setLongitude(null);
                localisationRepository.saveAndFlush(address);
                return true;
            } catch (Exception e) {
                return false;
            }
        }


        @Override
        public Localisation findOneById (Long id){
            return localisationRepository.findById(id)
                    .orElseThrow(EntityNotFoundException::new);
        }
    }

