package it.epicode.wrestlingpromo.on_loans;

import it.epicode.wrestlingpromo.wrestlers.Wrestler;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "wrestlers on loan")
public class RegisterWrestlersOnLoan {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToMany
    private List<Wrestler> wrestlers = new ArrayList<>();

    private boolean isReturned = false;

    @ManyToOne
    private RegisterOnLoan registerOnLoan;

}
