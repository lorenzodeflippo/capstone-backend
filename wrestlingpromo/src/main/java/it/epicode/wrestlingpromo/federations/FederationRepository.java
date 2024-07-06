package it.epicode.wrestlingpromo.federations;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FederationRepository extends JpaRepository<Federation, Long> {
    public Federation findByName(String name);
    public boolean existsByName(String name);

    public List<FederationResponsePrj> findAllFederationResponsePrj();

}
