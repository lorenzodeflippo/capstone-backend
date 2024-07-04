package it.epicode.wrestlingpromo.wrestlers;

import lombok.Data;

import java.util.List;

@Data
public class CreateWrstlrAndMngrRequest {
    private String ringName;
    private Long idGeneralManager;
    private List<Long> idRosters;
    private Long idFaction;
    private String name;



}
