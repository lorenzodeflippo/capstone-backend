package it.epicode.wrestlingpromo.wrestlers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import it.epicode.wrestlingpromo.factions.Faction;
import it.epicode.wrestlingpromo.general_managers.GeneralManager;
import it.epicode.wrestlingpromo.managers.Manager;
import it.epicode.wrestlingpromo.rosters.Roster;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;


import java.util.List;

@Entity
@Data
@Table(name = "wrestlers")
public class Wrestler {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String ringname;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @ToString.Exclude
    @JsonIgnoreProperties({"wrestlers","id"})
    private Manager manager;

    @ManyToOne
    @ToString.Exclude
    @JsonIgnoreProperties("wrestler")
    private GeneralManager generalManager;

    @ManyToMany
    @ToString.Exclude
    @JsonIgnoreProperties("wrestler")
    private List<Roster> rosters;

    @ManyToOne
    @ToString.Exclude
    @JsonIgnoreProperties("wrestler")
    private Faction faction;
}
