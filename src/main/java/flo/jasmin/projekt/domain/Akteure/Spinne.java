package flo.jasmin.projekt.domain.Akteure;

import flo.jasmin.projekt.domain.Visualisierung.AsciiVisualisierung;
import flo.jasmin.projekt.domain.Gegenstaende.konkreteZutaten.Spinnenseide;

import java.util.ArrayList;


public class Spinne extends Gegner {
    
    public Spinne() {
        super(35, 3, 10, 12, 25, "Riesenspinne", new SpinneVisualisierung());
        getInventar().add(new Spinnenseide());
        if (Math.random() < 0.4) {
            getInventar().add(new Spinnenseide());
        }
    }
    

    @Override
    public Wesen ausgewaehltesZiel(ArrayList<Wesen> wesen) {
        Wesen schwaechstesZiel = wesen.get(0);
        for (Wesen w : wesen) {
            if (w.getGesundheit() < schwaechstesZiel.getGesundheit()) {
                schwaechstesZiel = w;
            }
        }
        return schwaechstesZiel;
    }
    
    private static class SpinneVisualisierung implements AsciiVisualisierung {
        @Override
        public String getVisualisierung() {
            return """
                     Riesenspinne 
                      /\\___/\\
                     ( o   o )
                      )  .  (
                     /|\\___/|\\
                    / | | | | \\
                    """;
        }
    }
}