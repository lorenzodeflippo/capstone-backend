package it.epicode.wrestlingpromo.federations;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class Request {
    @NotEmpty(message = "il nome è obbligatorio")
    private String name;
}
