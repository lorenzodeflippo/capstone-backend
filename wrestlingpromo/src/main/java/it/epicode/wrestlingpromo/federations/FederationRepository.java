package it.epicode.wrestlingpromo.federations;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FederationRepository extends JpaRepository<Federation, Long> {
    public Federation findByName(String name);
    public boolean existsByName(String name);
}
