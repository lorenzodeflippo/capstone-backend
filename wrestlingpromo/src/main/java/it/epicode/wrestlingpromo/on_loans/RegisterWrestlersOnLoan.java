package it.epicode.wrestlingpromo.on_loans;

import it.epicode.wrestlingpromo.wrestlers.Wrestler;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "wrestlers on loan")
public class RegisterWrestlersOnLoan {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToMany //(mappedBy = "wrestlers on loan")
    private Wrestler wrestler;

    private boolean isReturned = false;

    @ManyToOne //(mappedBy = "")
    private RegisterOnLoan registerOnLoan;


}
