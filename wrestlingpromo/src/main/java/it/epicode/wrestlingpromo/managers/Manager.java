package it.epicode.wrestlingpromo.managers;

import it.epicode.wrestlingpromo.wrestlers.Wrestler;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "managers")
@Data
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 50, unique = true)
    private String name;

    @OneToMany(mappedBy = "manager")
    @ToString.Exclude
    private List<Wrestler> wrestler;

}
