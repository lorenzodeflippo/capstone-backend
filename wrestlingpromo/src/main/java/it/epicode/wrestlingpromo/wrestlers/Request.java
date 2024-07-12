package it.epicode.wrestlingpromo.wrestlers;

import lombok.Data;

import java.util.List;

@Data
public class Request {
    private String name;
    private List<Long> idRoster;
    private Long idManager;
    private Long idGeneralManager;
    private Long idFaction;
}