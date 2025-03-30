package fr.charly.businessCase.service;


import fr.charly.businessCase.DTO.MediaDTO;
import fr.charly.businessCase.entity.Media;
import fr.charly.businessCase.repository.MediaRepository;
import fr.charly.businessCase.service.interfaces.ServiceInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MediaService implements ServiceInterface<Media, String, MediaDTO, MediaDTO> {

    private MediaRepository mediaRepository;
    private ChargingStationService chargingStationService;

    @Override
    public Media create(MediaDTO o) {
        Media media = new Media();
        media.setExtension(o.getExtension());
        media.setName(o.getName());
        media.setChargingStation(chargingStationService.findOneById(o.getChargingStationId()));

        return mediaRepository.saveAndFlush(media);
    }

    @Override
    public Media update(MediaDTO o, String uuid) {
        Media media = findOneById(uuid);
        media.setName(o.getName());
        media.setExtension(o.getExtension());
        media.setChargingStation(chargingStationService.findOneById(o.getChargingStationId()));
        return mediaRepository.saveAndFlush(media);
    }

    @Override
    public Boolean delete(String uuid) {
        try {
            mediaRepository.deleteById(uuid);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public Media findOneById(String uuid) {
        return mediaRepository.findById(uuid)
                .orElseThrow(EntityNotFoundException::new);
    }
}
