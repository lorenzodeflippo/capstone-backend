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
        Response response = new Response();
        repository.save(entity);
        BeanUtils.copyProperties(entity,response);
        return response;

    }
}
