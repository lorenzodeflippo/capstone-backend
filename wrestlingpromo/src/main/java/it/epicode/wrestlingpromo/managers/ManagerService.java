package it.epicode.wrestlingpromo.managers;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerService {

    @Autowired
    private ManagerRepository repository;

    // GET ALL
    public List<Manager> findAll() {
        return repository.findAll();
    }

    //GET per ID
    public it.epicode.wrestlingpromo.managers.Response findById(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Manager non trovato");
        }
        Manager entity = repository.findById(id).get();
        it.epicode.wrestlingpromo.managers.Response response = new it.epicode.wrestlingpromo.managers.Response();

        BeanUtils.copyProperties(entity, response);
        return response;
    }

    // POST
    public it.epicode.wrestlingpromo.managers.Response create(it.epicode.wrestlingpromo.managers.Request request) {
        Manager entity = new Manager();

        BeanUtils.copyProperties(request, entity);
        it.epicode.wrestlingpromo.managers.Response response = new it.epicode.wrestlingpromo.managers.Response();

        BeanUtils.copyProperties(entity, response);
        repository.save(entity);
        return response;
    }

    // PUT
    public it.epicode.wrestlingpromo.managers.Response modify(Long id, Request request) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Manager non trovato");
        }
        Manager entity = repository.findById(id).get();

        BeanUtils.copyProperties(request, entity);
        repository.save(entity);
        it.epicode.wrestlingpromo.managers.Response response = new Response();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    //PATCH
    public Response modifyName(Long id,ChangeNameRequest request){
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Manager non trovato");
        }
        Manager entity = repository.findById(id).get();
        //BeanUtils.copyProperties(request, entity);
        entity.setName(request.getName());
        Response response = new Response();
        BeanUtils.copyProperties(entity, response);
        return response;

    }

    // DELETE
    public String delete(Long id) {
        if (!repository.existsById(id))
            throw new EntityNotFoundException("Manager non trovato");

        repository.deleteById(id);
        return "Manager eliminato";
    }
}