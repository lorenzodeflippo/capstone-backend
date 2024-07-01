package it.epicode.wrestlingpromo.rosters;

import it.epicode.wrestlingpromo.wrestlers.Wrestler;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "rosters")
@Data
public class Roster {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 50, unique = true)
    private String description;

    @OneToMany(mappedBy = "rosters")
    private List<Wrestler> wrestler;
}
