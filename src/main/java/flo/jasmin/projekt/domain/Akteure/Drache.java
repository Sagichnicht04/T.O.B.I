package flo.jasmin.projekt.domain.Akteure;

import flo.jasmin.projekt.domain.Visualisierung.AsciiVisualisierung;
import flo.jasmin.projekt.domain.Gegenstaende.konkreteZutaten.Drachenschuppe;
import flo.jasmin.projekt.domain.Gegenstaende.konkreteAusstattung.Drachenschwert;

import java.util.ArrayList;

public class Drache extends Gegner {
    
    public Drache() {
        super(150, 15, 25, 8, 200, "Feuerdrache", new DrachenVisualisierung());
        getInventar().add(new Drachenschuppe());
        getInventar().add(new Drachenschuppe());
        if (Math.random() < 0.5) {
            getInventar().add(new Drachenschuppe());
        }
        if (Math.random() < 0.3) {
            getInventar().add(new Drachenschwert());
        }
    }
    
    @Override
    public Wesen ausgewähltesZiel(ArrayList<Wesen> wesen) {
        Wesen gefährlichstesZiel = wesen.get(0);
        for (Wesen w : wesen) {
            if (w.getStats().getAngriff() > gefährlichstesZiel.getStats().getAngriff()) {
                gefährlichstesZiel = w;
            }
        }
        return gefährlichstesZiel;
    }
    
    private static class DrachenVisualisierung implements AsciiVisualisierung {
        @Override
        public String getVisualisierung() {
            return """
                    🐉 FEUERDRACHE 🐉
                           />
                          //
                     /\\  //
                    /  \\//
                   / /\\  \\
                  / /  \\  \\
                 /_/    \\__\\
                    """;
        }
    }
}