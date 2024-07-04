package it.epicode.wrestlingpromo.on_loans;

import it.epicode.wrestlingpromo.federations.Federation;
import lombok.Data;

import java.util.List;

@Data
public class CreateOnLoanRequest {

    private String name;
    private List<Long> idWrestlers;
}
