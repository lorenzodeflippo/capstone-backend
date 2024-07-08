package it.epicode.wrestlingpromo.general_managers;

import it.epicode.wrestlingpromo.wrestlers.Wrestler;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "general_managers")
@Data
public class GeneralManager {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 50, unique = true)

    private String name;

    @OneToMany(mappedBy = "generalManager")
    @ToString.Exclude
    private List<Wrestler> wrestler;


}
