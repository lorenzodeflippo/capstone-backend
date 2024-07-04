package it.epicode.wrestlingpromo.on_loans;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Response {
    private Long id;
    private int numberWrestlersOnLoan;
    private List<String> ringnameWrestlersOnLoan = new ArrayList<>();
}
