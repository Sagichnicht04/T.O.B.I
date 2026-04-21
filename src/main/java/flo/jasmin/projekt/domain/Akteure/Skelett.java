package flo.jasmin.projekt.domain.Akteure;

import flo.jasmin.projekt.domain.Visualisierung.AsciiVisualisierung;
import flo.jasmin.projekt.domain.Gegenstaende.konkreteZutaten.Knochen;
import flo.jasmin.projekt.domain.Gegenstaende.konkreteAusstattung.Eisenschwert;


public class Skelett extends Gegner {
    
    public Skelett() {
        super(60, 8, 12, 5, 40, "Skelett", new SkeletonVisualisierung());
        if (Math.random() < 0.6) {
            getInventar().add(new Knochen());
        }
        if (Math.random() < 0.15) {
            getInventar().add(new Eisenschwert());
        }
    }
    
    private static class SkeletonVisualisierung implements AsciiVisualisierung {
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