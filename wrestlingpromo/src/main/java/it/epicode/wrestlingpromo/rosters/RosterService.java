package it.epicode.wrestlingpromo.rosters;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RosterService {

    @Autowired
    private RosterRepository repository;

    // GET ALL
    public List<Roster> findAll() {
        return repository.findAll();
    }

    //GET per ID
    public it.epicode.wrestlingpromo.rosters.Response findById(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Roster non trovato");
        }
        Roster entity = repository.findById(id).get();
        it.epicode.wrestlingpromo.rosters.Response response = new it.epicode.wrestlingpromo.rosters.Response();

        BeanUtils.copyProperties(entity, response);
        return response;
    }

    // POST
    public it.epicode.wrestlingpromo.rosters.Response create(it.epicode.wrestlingpromo.rosters.Request request) {
        Roster entity = new Roster();

        BeanUtils.copyProperties(request, entity);
        it.epicode.wrestlingpromo.rosters.Response response = new it.epicode.wrestlingpromo.rosters.Response();

        BeanUtils.copyProperties(entity, response);
        repository.save(entity);
        return response;
    }

    // PUT
    public it.epicode.wrestlingpromo.rosters.Response modify(Long id, Request request) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Roster non trovato");
        }
        Roster entity = repository.findById(id).get();

        BeanUtils.copyProperties(request, entity);
        repository.save(entity);
        it.epicode.wrestlingpromo.rosters.Response response = new it.epicode.wrestlingpromo.rosters.Response();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    // DELETE
    public String delete(Long id) {
        if (!repository.existsById(id))
            throw new EntityNotFoundException("Roster non trovato");

        repository.deleteById(id);
        return "Roster eliminato";
    }
}