package it.epicode.wrestlingpromo.general_managers;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneralManagerService {

    @Autowired
    private GeneralManagerRepository generalManagerRepository;

    public List<GeneralManager> findAll() {
        return generalManagerRepository.findAll();
    }

    public GeneralManagerResponse findById(Long id) {
        if (!generalManagerRepository.existsById(id)) {
            throw new EntityNotFoundException("General Manager non trovato");
        }
        GeneralManager generalManager = generalManagerRepository.findById(id).get();
        GeneralManagerResponse generalManagerResponse = new GeneralManagerResponse();

        BeanUtils.copyProperties(generalManager, generalManagerResponse);
        return generalManagerResponse;
    }

    public GeneralManagerResponse create(GeneralManagerRequest generalManagerRequest) {
        GeneralManager generalManager = new GeneralManager();

        BeanUtils.copyProperties(generalManagerRequest, generalManager);
        GeneralManagerResponse generalManagerResponse = new GeneralManagerResponse();

        BeanUtils.copyProperties(generalManager, generalManagerResponse);
        generalManagerRepository.save(generalManager);
        return generalManagerResponse;
    }

    public GeneralManagerResponse modify(Long id, GeneralManagerRequest generalManagerRequest) {
        if (!generalManagerRepository.existsById(id)) {
            throw new EntityNotFoundException("General Manager non trovato");
        }
        GeneralManager generalManager = generalManagerRepository.findById(id).get();

        BeanUtils.copyProperties(generalManagerRequest, generalManager);
        generalManagerRepository.save(generalManager);
        GeneralManagerResponse generalManagerResponse = new GeneralManagerResponse();
        BeanUtils.copyProperties(generalManager, generalManagerResponse);
        return generalManagerResponse;
    }

    public String delete(Long id) {
        if (!generalManagerRepository.existsById(id))
            throw new EntityNotFoundException("General Manager non trovato");

        generalManagerRepository.deleteById(id);
        return "General Manager eliminato";
    }
}