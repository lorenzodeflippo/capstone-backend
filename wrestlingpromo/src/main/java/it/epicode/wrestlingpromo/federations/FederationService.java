package it.epicode.wrestlingpromo.federations;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FederationService {

    @Autowired
    private FederationRepository repository;

    // GET ALL
    public List<Federation> findAll() {
        return repository.findAll();
    }

    //GET per ID
    public Response findById(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Federation non trovato");
        }
        Federation entity = repository.findById(id).get();
        Response response = new Response();

        BeanUtils.copyProperties(entity, response);
        return response;
    }

    // POST
    public Response create(Request request) {
        if(repository.existsByName(request.getName())){
            throw new EntityExistsException("The Federation already exists");
        }
        Federation entity = new Federation();
        BeanUtils.copyProperties(request, entity);
        Response response = new Response();
        BeanUtils.copyProperties(entity, response);
        repository.save(entity);
        return response;
    }

    // PUT
    public Response modify(Long id, Request request) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Federation non trovato");
        }
        Federation entity = repository.findById(id).get();

        BeanUtils.copyProperties(request, entity);
        repository.save(entity);
        Response response = new Response();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    // DELETE
    public String delete(Long id) {
        if (!repository.existsById(id))
            throw new EntityNotFoundException("Federation non trovato");

        repository.deleteById(id);
        return "Federation eliminato";
    }
}