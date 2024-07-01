package it.epicode.wrestlingpromo.wrestlers;

import it.epicode.wrestlingpromo.factions.Faction;
import it.epicode.wrestlingpromo.factions.FactionRepository;
import it.epicode.wrestlingpromo.general_managers.GeneralManager;
import it.epicode.wrestlingpromo.general_managers.GeneralManagerRepository;
import it.epicode.wrestlingpromo.managers.Manager;
import it.epicode.wrestlingpromo.managers.ManagerRepository;
import it.epicode.wrestlingpromo.rosters.Roster;
import it.epicode.wrestlingpromo.rosters.RosterRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor //no need multi autowired
public class WrestlerService {

    private final WrestlerRepository repository;
    private final RosterRepository rosterRepository;
    private final GeneralManagerRepository generalManagerRepository;
    private final ManagerRepository managerRepository;
    private final FactionRepository factionRepository;

    //POST
    public Response create(Request request){
        if(!managerRepository.existsById(request.getIdManager())){
            throw new EntityNotFoundException("Manager not found");
        }
        if(!generalManagerRepository.existsById(request.getIdGeneralManager())){
            throw new EntityNotFoundException("General Manager not found");
        }
        if(!factionRepository.existsById(request.getIdFaction())){
            throw new EntityNotFoundException("Faction not found");
        }
        Wrestler entity = new Wrestler();
        BeanUtils.copyProperties(request, entity);
        Manager manager = managerRepository.findById(request.getIdManager()).get();
        GeneralManager generalManager = generalManagerRepository.findById(request.getIdGeneralManager()).get();
        List<Roster> rosters = rosterRepository.findAllById(request.getIdRoster());
        Faction faction = factionRepository.findById(request.getIdFaction()).get();
        entity.setManager(manager);
        entity.setGeneralManager(generalManager);
        entity.setRosters(rosters);
        entity.setFaction(faction);
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
        if(!factionRepository.existsById(request.getIdFaction())){
            throw new EntityNotFoundException("Faction not found");
        }
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Wrestler not found");
        }

        Wrestler entity = repository.findById(id).get();
        Manager manager = managerRepository.findById(request.getIdManager()).get();
        GeneralManager generalManager = generalManagerRepository.findById(request.getIdGeneralManager()).get();
        List<Roster> rosters = rosterRepository.findAllById(request.getIdRoster());
        Faction faction = factionRepository.findById(request.getIdFaction()).get();
        BeanUtils.copyProperties(request, entity);
        entity.setManager(manager);
        entity.setGeneralManager(generalManager);
        entity.setRosters(rosters);
        entity.setFaction(faction);
        repository.save(entity);
        Response response = new Response();
        BeanUtils.copyProperties(entity, response);
        return response;
    }
    // PUT Optional for example
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

    // GET

    public List<Wrestler> findAll(){
        return repository.findAll();
    }
    @Transactional
    public CompleteResponse findById(Long id){
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Wrestler not found");
        }
        Wrestler entity = repository.findById(id).get();
        CompleteResponse completeResponse = new CompleteResponse();
        BeanUtils.copyProperties(entity, completeResponse);
        completeResponse.setRosters(entity.getRosters());
        it.epicode.wrestlingpromo.managers.LightResponse managerResponse = new it.epicode.wrestlingpromo.managers.LightResponse();
        BeanUtils.copyProperties(entity.getManager(), managerResponse);
        it.epicode.wrestlingpromo.general_managers.Response generalManagerResponse = new it.epicode.wrestlingpromo.general_managers.Response();
        BeanUtils.copyProperties(entity.getGeneralManager(), generalManagerResponse);
        it.epicode.wrestlingpromo.factions.Response factionResponse = new it.epicode.wrestlingpromo.factions.Response();
        BeanUtils.copyProperties(entity.getFaction(), factionResponse);
        completeResponse.setManager(managerResponse);
        completeResponse.setGeneralManager(generalManagerResponse);
        completeResponse.setFaction(factionResponse);
        return completeResponse;

    }

    //GET with queryRepository for recuperate name;
    public List<Wrestler> findByManager(Manager manager) {
        return repository.findByManager(manager);
    }
    public List<Wrestler> findByGeneralManager(GeneralManager generalManager){
        return repository.findByGeneralManager(generalManager);
    }

    //DELETE
    public String delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Wrestler not found");
        }
        repository.deleteById(id);
        return "Wrestler eliminated";
    }
}
