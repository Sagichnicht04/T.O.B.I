package flo.jasmin.projekt.domain.NPCs;

import flo.jasmin.projekt.domain.Akteure.TeamWesen;
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
                  "Gerne leiste ich euch Gesellschaft, wenn ihr erlaubt."
              )),
              new EinsiedlerVisualisierung(),
              new TeamWesen(20,10,20,5,10, "Einsiedler Toni")
        );
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