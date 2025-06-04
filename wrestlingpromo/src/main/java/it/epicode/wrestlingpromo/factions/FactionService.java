package it.epicode.wrestlingpromo.factions;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FactionService {

    @Autowired
    private FactionRepository repository;

    // GET ALL
    public List<Faction> findAll() {
        return repository.findAll();
    }

    //GET per ID
    public it.epicode.wrestlingpromo.factions.Response findById(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Faction non trovata");
        }
        Faction entity = repository.findById(id).get();
        //TODO quando si creano delle classi per le request e le response o comunque in generale è sempre
        // meglio inserire nel nome a cosa fanno riferimento ad esempio FactionsResponse perche altrimenti
        // viene riscritto tutto il package che sporca il codice o ad esempio per le request il caso d'uso,
        // ad esempio SearchFactionByNameRequest, come buona pratica è meglio usare i record per le response e le request
        // che sono oggetti immutabili
        it.epicode.wrestlingpromo.factions.Response response = new it.epicode.wrestlingpromo.factions.Response();

        //TODO al posto di utilizzare questo metodo di utility sarebbe meglio fare un layer di mapping che solitamente viene utilizzato
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    // POST
    public it.epicode.wrestlingpromo.factions.Response create(it.epicode.wrestlingpromo.factions.Request request) {
        Faction entity = new Faction();

        BeanUtils.copyProperties(request, entity);
        it.epicode.wrestlingpromo.factions.Response response = new it.epicode.wrestlingpromo.factions.Response();

        BeanUtils.copyProperties(entity, response);
        repository.save(entity);
        return response;
    }

    // PUT
    public it.epicode.wrestlingpromo.factions.Response modify(Long id, Request request) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Faction non trovata");
        }
        Faction entity = repository.findById(id).get();

        BeanUtils.copyProperties(request, entity);
        repository.save(entity);
        it.epicode.wrestlingpromo.factions.Response response = new Response();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    // DELETE
    public String delete(Long id) {
        if (!repository.existsById(id))
            throw new EntityNotFoundException("Faction non trovata");

        repository.deleteById(id);
        return "Faction eliminata";
    }
}