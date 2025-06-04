package it.epicode.wrestlingpromo.factions;

import org.springframework.data.jpa.repository.JpaRepository;
//TODO qui usare l'annotation @Repository 
public interface FactionRepository extends JpaRepository<Faction, Long > {
}
