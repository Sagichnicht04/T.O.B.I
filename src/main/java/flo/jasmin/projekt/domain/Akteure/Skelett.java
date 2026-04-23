package flo.jasmin.projekt.domain.Akteure;

import flo.jasmin.projekt.domain.Visualisierung.AsciiVisualisierung;
import flo.jasmin.projekt.domain.Gegenstaende.Zutaten.Knochen;
import flo.jasmin.projekt.domain.Gegenstaende.Ausstattungen.Eisenschwert;


public class Skelett extends Gegner {
    
    public Skelett() {
        super(60, 8, 12, 5, 40, "Skelett", new SkelettVisualisierung());
        if (Math.random() < 0.6) {
            getInventar().add(new Knochen());
        }
        if (Math.random() < 0.15) {
            getInventar().add(new Eisenschwert());
        }
    }
    
    private static class SkelettVisualisierung implements AsciiVisualisierung {
        @Override
        public String getVisualisierung() {
            return """
                    Skelett
                       _____
                      /     \\
                     | () () |
                      \\  ^  /
                       |||||
                       |||||
                      /|   |\\
                     / |   | \\
                    """;
        }
    }
}