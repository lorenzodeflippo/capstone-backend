package it.epicode.wrestlingpromo.factions;

import it.epicode.wrestlingpromo.wrestlers.Wrestler;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@Table(name = "factions")
public class Faction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private int numberMembers;

    @OneToMany(mappedBy = "faction")
    @ToString.Exclude
    private List<Wrestler> wrestlers;
}