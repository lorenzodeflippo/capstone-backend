package it.epicode.wrestlingpromo.wrestlers;

import it.epicode.wrestlingpromo.general_managers.GeneralManager;
import it.epicode.wrestlingpromo.general_managers.GeneralManagerRepository;
import it.epicode.wrestlingpromo.managers.Manager;
import it.epicode.wrestlingpromo.managers.ManagerRepository;
import it.epicode.wrestlingpromo.rosters.Roster;
import it.epicode.wrestlingpromo.rosters.RosterRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WrestlerService {

    @Autowired
    WrestlerRepository repository;
    @Autowired
    RosterRepository rosterRepository;
    @Autowired
    GeneralManagerRepository generalManagerRepository;
    @Autowired
    ManagerRepository managerRepository;

    //POST
    public Response create(Request request){
        if(!managerRepository.existsById(request.getIdManager())){
            throw new EntityNotFoundException("Manager not found");
        }
        if(!generalManagerRepository.existsById(request.getIdGeneralManager())){
            throw new EntityNotFoundException("General Manager not found");
        }
        Wrestler entity = new Wrestler();
        BeanUtils.copyProperties(request, entity);
        Manager manager = managerRepository.findById(request.getIdManager()).get();
        GeneralManager generalManager = generalManagerRepository.findById(request.getIdGeneralManager()).get();
        List<Roster> rosters = rosterRepository.findAllById(request.getIdRoster());
        entity.setManager(manager);
        entity.setGeneralManager(generalManager);
        entity.setRosters(rosters);
        Response response = new Response();
        repository.save(entity);
        BeanUtils.copyProperties(entity,response);
        return response;
    }

    //PUT
    public Response modify(Long id, Request request){
        if(!managerRepository.existsById(request.getIdManager())){
            throw new EntityNotFoundException("Manager not found");
        }
        if(!generalManagerRepository.existsById(request.getIdGeneralManager())){
            throw new EntityNotFoundException("General Manager not found");
        }
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Wrestler not found");
        }
        Wrestler entity = repository.findById(id).get();
        Manager manager = managerRepository.findById(request.getIdManager()).get();
        GeneralManager generalManager = generalManagerRepository.findById(request.getIdGeneralManager()).get();
        List<Roster> rosters = rosterRepository.findAllById(request.getIdRoster());
        BeanUtils.copyProperties(request, entity);
        entity.setManager(manager);
        entity.setGeneralManager(generalManager);
        entity.setRosters(rosters);
        repository.save(entity);
        Response response = new Response();
        BeanUtils.copyProperties(entity, response);
        return response;
    }
    // PUT Optional
        public Response modify1(Long id, Request request) {
        Optional<Manager> manager = managerRepository.findById(id);
        if (manager.isEmpty()) {
            throw new EntityNotFoundException("Manager not found");
        }
        Optional<GeneralManager> generalManager = generalManagerRepository.findById(request.getIdGeneralManager());
        if (generalManager.isEmpty()) {
            throw new EntityNotFoundException("General Manager not found");
        }
        Optional<Wrestler> wrestler = repository.findById(id);
        if (wrestler.isEmpty()) {
            throw new EntityNotFoundException("Wrestler not found");
        }
        List<Roster> roster = rosterRepository.findAllById(request.getIdRoster());
        wrestler.get().setManager(manager.get());
        wrestler.get().setRosters(roster);
        wrestler.get().setGeneralManager(generalManager.get());
        BeanUtils.copyProperties(request, wrestler);
        repository.save(wrestler.get());
        Response response = new Response();
        BeanUtils.copyProperties(wrestler, response);
        return response;
    }
}
