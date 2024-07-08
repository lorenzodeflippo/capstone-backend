package it.epicode.wrestlingpromo.wrestlers;

import it.epicode.wrestlingpromo.factions.Faction;
import it.epicode.wrestlingpromo.general_managers.GeneralManager;
import it.epicode.wrestlingpromo.managers.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WrestlerRepository extends JpaRepository<Wrestler, Long> {
    public Wrestler findByRingname(String ringname);
    public List<Wrestler> findByManager(Manager manager);
    public List<Wrestler> findByGeneralManager(GeneralManager generalManager);

    public List<Wrestler> findByFaction(Faction faction);

    public boolean existsByRingname(String ringname);

    public List<WrestlerResponsePrj> findAllBy();
    
}