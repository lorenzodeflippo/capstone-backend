package it.epicode.wrestlingpromo.federations;

import it.epicode.wrestlingpromo.on_loans.RegisterOnLoan;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "federations")
public class Federation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "federation", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<RegisterOnLoan> onLoans;
}
