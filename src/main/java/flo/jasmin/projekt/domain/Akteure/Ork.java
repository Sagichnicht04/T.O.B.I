package flo.jasmin.projekt.domain.Akteure;

import flo.jasmin.projekt.domain.Visualisierung.AsciiVisualisierung;
import flo.jasmin.projekt.domain.Gegenstaende.Zutaten.Fleisch;
import flo.jasmin.projekt.domain.Gegenstaende.Ausstattungen.Axt;

public class Ork extends Gegner {
    
    public Ork() {
        super(80, 6, 18, 4, 60, "Ork-Krieger", new OrkVisualisierung());
        if (Math.random() < 0.5) {
            getInventar().add(new Fleisch());
        }
        if (Math.random() < 0.25) {
            getInventar().add(new Axt());
        }
    }
    

    @Override
    public Wesen ausgewaehltesZiel(java.util.ArrayList<Wesen> wesen) {
        Wesen staerkstes = wesen.get(0);
        for (Wesen w : wesen) {
            if (w.getGesundheit() > staerkstes.getGesundheit()) {
                staerkstes = w;
            }
        }
        return staerkstes;
    }
    
    private static class OrkVisualisierung implements AsciiVisualisierung {
        @Override
        public String getVisualisierung() {
            return """
                    ⚔ Ork-Krieger ⚔
                       _____
                      / O O \\
                     |   >   |
                      \\ === /
                       |||||
                      /|||||\\
                     / ||||| \\
                    """;
        }
    }
}