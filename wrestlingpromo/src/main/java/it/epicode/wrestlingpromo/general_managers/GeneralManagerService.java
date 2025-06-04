package it.epicode.wrestlingpromo.general_managers;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneralManagerService {
    //TODO al posto dell'autowired è meglio usare il @RequiredArgsConstructor di lombok e rendere il campo iniettato final
    @Autowired
    private GeneralManagerRepository repository;

    // GET ALL
    public List<GeneralManager> findAll() {
        return repository.findAll();
    }

    //GET per ID
    public Response findById(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("General Manager non trovato");
        }
        GeneralManager entity = repository.findById(id).get();
        Response response = new Response();

        BeanUtils.copyProperties(entity, response);
        return response;
    }

    // POST
    public Response create(Request request) {
        GeneralManager entity = new GeneralManager();

        BeanUtils.copyProperties(request, entity);
        Response response = new Response();

        BeanUtils.copyProperties(entity, response);
        repository.save(entity);
        return response;
    }

    // PUT
    public Response modify(Long id, Request request) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("General Manager non trovato");
        }
        GeneralManager entity = repository.findById(id).get();

        BeanUtils.copyProperties(request, entity);
        repository.save(entity);
        Response response = new Response();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    // DELETE
    public String delete(Long id) {
        if (!repository.existsById(id))
            throw new EntityNotFoundException("General Manager non trovato");

        repository.deleteById(id);
        return "General Manager eliminato";
    }
}