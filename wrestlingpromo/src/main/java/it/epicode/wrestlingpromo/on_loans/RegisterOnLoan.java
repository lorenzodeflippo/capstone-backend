package it.epicode.wrestlingpromo.on_loans;

import it.epicode.wrestlingpromo.federations.Federation;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "on loans")
public class RegisterOnLoan {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private double numberLoan;

    private LocalDate dateLoan;

    @ManyToOne
    private Federation federation;

    @OneToMany
    private List<RegisterWrestlersOnLoan> registerWrestlersOnLoanList;
}
