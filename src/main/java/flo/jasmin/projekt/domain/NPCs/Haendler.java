package flo.jasmin.projekt.domain.NPCs;

import flo.jasmin.projekt.domain.Akteure.TeamWesen;
import flo.jasmin.projekt.domain.Visualisierung.AsciiVisualisierung;

import java.util.ArrayList;
import java.util.List;

public class Haendler extends NPC {
    
    public Haendler() {
        super("Haendler Grimwald",
              new ArrayList<>(List.of(
                  "Willkommen, Reisender! Ich bin Grimwald, ein Haendler der durch diese Lande zieht.",
                  "In den Bergen im Norden lauern gefaehrliche Orks. Sie sind stark, aber langsam.",
                  "Passt auf die Spinnen in den Waeldern auf. Sie sind schnell und greifen die Schwaechsten an!",
                  "Im alten Friedhof im Osten wandeln Skelette. Sie droppen wertvolle Knochen fuer Bruehe.",
                  "Ich habe schon lange keine Geschaefte mehr gemacht. Ich wuerde mich freuen, euch auf eurer Reise zu begleiten."
              )),
              new HaendlerVisualisierung(),
        new TeamWesen(50,5,5,0,0, "Grimwald"));

    }
    
    private static class HaendlerVisualisierung implements AsciiVisualisierung {
        @Override
        public String getVisualisierung() {
            return """
                    Haendler Grimwald
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