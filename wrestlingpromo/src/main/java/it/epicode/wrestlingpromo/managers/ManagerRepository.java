package it.epicode.wrestlingpromo.managers;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ManagerRepository extends JpaRepository<Manager, Long> {

    public List<ManagerResponsePrj> findAllBy();

    public List<Manager> findAllByName(String name);


}
