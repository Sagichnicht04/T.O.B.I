package flo.jasmin.projekt.domain.NPCs;

import flo.jasmin.projekt.domain.Akteure.TeamWesen;
import flo.jasmin.projekt.domain.Visualisierung.TobiVisualisierung;

import java.util.ArrayList;
import java.util.List;

public class Tobi extends NPC {
    public Tobi() {
        super(
                "Tobi",
                new ArrayList<>(List.of("Nanu? Wer bist denn du?", "Wusstest du das man nicht weiss ob ein np schweres Problem vielleicht doch nur p schwer ist?", "Darf ich mitkommen?!")),
                new TobiVisualisierung(),
                new TeamWesen(10,5,10,0,0, "Tobi")
        );

    }
}
