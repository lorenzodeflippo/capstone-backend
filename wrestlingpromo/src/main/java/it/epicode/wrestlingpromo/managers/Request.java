package it.epicode.wrestlingpromo.managers;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

    @Data
    public class Request {

        @NotEmpty(message = "Il nome non può essere vuoto")
        private String name;
    }

