package flo.jasmin.projekt.domain.NPCs;

import flo.jasmin.projekt.domain.Akteure.TeamWesen;
import flo.jasmin.projekt.domain.Visualisierung.AsciiVisualisierung;

import java.util.ArrayList;
import java.util.List;

public class Haendler extends NPC {
    
    public Haendler() {
        super("Händler Grimwald",
              new ArrayList<>(List.of(
                  "Willkommen, Reisender! Ich bin Grimwald, ein Händler der durch diese Lande zieht.",
                  "In den Bergen im Norden lauern gefährliche Orks. Sie sind stark, aber langsam.",
                  "Passt auf die Spinnen in den Wäldern auf. Sie sind schnell und greifen die Schwächsten an!",
                  "Im alten Friedhof im Osten wandeln Skelette. Sie droppen wertvolle Knochen für Brühe.",
                  "Ich habe schon lange keine Geschäfte mehr gemacht. Ich würde mich freuen, euch auf eurer Reise zu begleiten."
              )),
              new HaendlerVisualisierung(),
        new TeamWesen(50,5,5,0,0, "Grimwald"));

    }
    
    private static class HaendlerVisualisierung implements AsciiVisualisierung {
        @Override
        public String getVisualisierung() {
            return """
                    Händler Grimwald
                       _____
                      /     \\
                     | ^   ^ |
                      \\  -  /
                       |||||
                      [=====]
                      /|   |\\
                     / |   | \\
                    """;
        }
    }
}