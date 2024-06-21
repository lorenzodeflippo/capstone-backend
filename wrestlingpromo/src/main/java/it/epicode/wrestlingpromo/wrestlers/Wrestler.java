package it.epicode.wrestlingpromo.wrestlers;

import it.epicode.wrestlingpromo.general_managers.GeneralManager;
import it.epicode.wrestlingpromo.managers.Manager;
import it.epicode.wrestlingpromo.rosters.Roster;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.jdbc.core.SqlRowSetResultSetExtractor;

import java.util.List;

@Entity
@Data
@Table(name = "wrestlers")
public class Wrestler {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    @ManyToOne
    private Manager manager;

    @ManyToOne
    private GeneralManager generalManager;

    @ManyToMany
    private List<Roster> rosters;
}
