package flo.jasmin.projekt.domain.NPCs;

import flo.jasmin.projekt.domain.Akteure.NPC;
import flo.jasmin.projekt.domain.Visualisierung.AsciiVisualisierung;

import java.util.ArrayList;
import java.util.List;

public class Einsiedler extends NPC {
    
    public Einsiedler() {
        super("Der Einsiedler",
              new ArrayList<>(List.of(
                  "Ah... Besucher. Selten kommen welche bis hier herauf.",
                  "Ihr sucht Macht? Dann müsst ihr die Drachen besiegen.",
                  "Drachen sind intelligent und wählen ihre Ziele strategisch.",
                  "Die Lederrüstung im Dorf bietet guten Schutz ohne zu verlangsamen.",
                  "Geht nun. Meine Einsamkeit ruft..."
              )),
              new EinsiedlerVisualisierung());
    }
    
  
    private static class EinsiedlerVisualisierung implements AsciiVisualisierung {
        @Override
        public String getVisualisierung() {
            return """
                    🧙 Der Einsiedler 🧙
                       _____
                      /     \\
                     | .   . |
                      \\  _  /
                       |||||
                      //|||\\\\
                     // ||| \\\\
                    """;
        }
    }
}