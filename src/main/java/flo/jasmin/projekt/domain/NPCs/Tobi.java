package flo.jasmin.projekt.domain.NPCs;

import flo.jasmin.projekt.domain.Akteure.NPC;
import flo.jasmin.projekt.domain.Visualisierung.TobiVisualisierung;

import java.util.ArrayList;
import java.util.List;

public class Tobi extends NPC {
    public Tobi() {
        super(
                "Tobi",
                new ArrayList<>(List.of("Nanu? Wer bist denn du?", "Wusstest du das man nicht weiß ob ein np schweres Problem vielleicht doch nur p schwer ist?", "Pass auf dich auf!")),
                new TobiVisualisierung()
        );

    }
}
