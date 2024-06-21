package it.epicode.wrestlingpromo.general_managers;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "general_managers")
@Data
public class GeneralManager {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 50, unique = true)

    private String name;
}
