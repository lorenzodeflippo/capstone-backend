package it.epicode.wrestlingpromo.wrestlers;

import it.epicode.wrestlingpromo.rosters.Roster;
import lombok.Data;

import java.util.List;

@Data
public class CompleteResponse {
    private Long id;
    private String name;
    private it.epicode.wrestlingpromo.managers.Response manager;
    private it.epicode.wrestlingpromo.general_managers.Response generalManager;
    private List<Roster> rosters;
}
