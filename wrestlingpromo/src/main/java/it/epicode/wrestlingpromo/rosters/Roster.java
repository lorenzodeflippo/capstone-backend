package it.epicode.wrestlingpromo.rosters;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "rosters")
@Data
public class Roster {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 50, unique = true)
    private String description;
}
