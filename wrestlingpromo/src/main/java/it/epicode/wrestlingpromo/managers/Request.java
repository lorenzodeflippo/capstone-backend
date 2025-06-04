package it.epicode.wrestlingpromo.managers;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

    @Data
    public class Request {
        //TODO i messaggi sarebbe meglio metterrli in un file di properties e poi accedervi tramite "${nome.della.propery}"
        @NotEmpty(message = "Il nome non può essere vuoto")
        private String name;
    }

