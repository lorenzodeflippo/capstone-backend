package it.epicode.wrestlingpromo.wrestlers;

import it.epicode.wrestlingpromo.rosters.Roster;
import lombok.Data;

import java.util.List;

@Data
public class CompleteResponse {
    private Long id;
    private String ringname;
    private it.epicode.wrestlingpromo.managers.LightResponse manager;
    private it.epicode.wrestlingpromo.general_managers.Response generalManager;
    private List<Roster> rosters;
    private it.epicode.wrestlingpromo.factions.Response faction;
}
