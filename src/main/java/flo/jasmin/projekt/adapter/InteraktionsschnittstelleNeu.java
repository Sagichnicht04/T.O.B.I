package flo.jasmin.projekt.adapter;

import flo.jasmin.projekt.application.Spiel;
import flo.jasmin.projekt.domain.Akteure.TeamWesen;
import flo.jasmin.projekt.domain.Befehl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InteraktionsschnittstelleNeu {
    private Spiel spiel;
    private Scanner scanner;

    public InteraktionsschnittstelleNeu() {
        this.spiel = new Spiel();
        this.scanner = new Scanner(System.in);
    }

    public void starte() {
        zeigeWillkommensNachricht();
        zeigeSpielStatus();

        while (true) {
            String eingabe = leseEingabe();
            if (eingabe.equalsIgnoreCase("ENDE")) {
                zeigeTschüssNachricht();
                break;
            }
            System.out.println("\n".repeat(30));
            verarbeiteEingabe(eingabe);
        }
    }

    private void zeigeWillkommensNachricht() {
        System.out.println("\n╔══════════════════════════════════════════════╗");
        System.out.println("║                                              ║");
        System.out.println("║          T.O.B.I - Text Adventure            ║");
        System.out.println("║                                              ║");
        System.out.println("╚══════════════════════════════════════════════╝");
        System.out.println("\nWillkommen in der Welt von T.O.B.I!");
        System.out.println("Dein Abenteuer beginnt jetzt...\n");
    }

    private void zeigeTschüssNachricht() {
        System.out.println("\n╔══════════════════════════════════════════════╗");
        System.out.println("║        Danke fürs Spielen! Bis bald!         ║");
        System.out.println("╚══════════════════════════════════════════════╝\n");
    }

    private void zeigeSpielStatus() {
        System.out.println("\n" + "═".repeat(60));
        
        zeigeTeamStatus();
        zeigeErlaubteBefehle();
        zeigePosition();
        
        System.out.println("═".repeat(60));
    }

    private void zeigeTeamStatus() {
        List<TeamWesen> team = new ArrayList<>(spiel.getTeam().getWesenInTeam());
        
        System.out.println("\nTEAM STATUS:");
        for (TeamWesen wesen : team) {
            String healthBar = createHealthBar(wesen.getGesundheit(), wesen.getMaxGesundheit());
            System.out.printf("  %-12s %s ❤️  %3d/%-3d │ ⚔️  ATK: %2d │ 🛡️  DEF: %2d%n",
                wesen.getName(),
                healthBar,
                wesen.getGesundheit(),
                wesen.getMaxGesundheit(),
                wesen.getAngriff(),
                wesen.getVerteidigung()
            );
        }
    }

    private void zeigeErlaubteBefehle() {
        System.out.println("\nVERFÜGBARE BEFEHLE:");
        System.out.print("  ");
        for (Befehl befehl : spiel.gibErlaubteBefehle()) {
            System.out.print(BefehleÜbersetzt.übersetze(befehl) + " │ ");
        }
        System.out.println("\nOder: ENDE (zum Beenden)");
    }

    private void zeigePosition() {
        System.out.println("\nPOSITION:");
        System.out.println("  " + spiel.getKarte().getMomentanePosition());
        System.out.println("  Ersparnisse: " + spiel.getTeam().getInventar().getErspartes() + " Münzen");
    }

    private String createHealthBar(int current, int max) {
        int barLength = 10;
        int filled = (int) Math.ceil((double) current / max * barLength);
        StringBuilder bar = new StringBuilder("[");
        for (int i = 0; i < barLength; i++) {
            if (i < filled) {
                bar.append("█");
            } else {
                bar.append("░");
            }
        }
        bar.append("]");
        return bar.toString();
    }

    private String leseEingabe() {
        System.out.print("\n➤ Deine Eingabe: ");
        return scanner.nextLine().trim();
    }

    private void verarbeiteEingabe(String eingabe) {
        String[] teile = eingabe.split("\\s+", 2);
        String befehlString = teile[0].toUpperCase();
        String parameter = teile.length > 1 ? teile[1] : "";

        try {
            Befehl befehl = BefehleÜbersetzt.übersetze(befehlString);

            if (!spiel.gibErlaubteBefehle().contains(befehl)) {
                System.out.println("\nDieser Befehl ist hier nicht erlaubt!");
                zeigeSpielStatus();
                return;
            }

            List<String> antworten = spiel.spieleBefehl(befehl, parameter);
            zeigeSpielStatus();

            System.out.println("\n" + "─".repeat(60));
            for (String antwort : antworten) {
                if (!antwort.trim().isEmpty() && !antwort.startsWith("Du willst")) {
                    System.out.println(antwort);
                }
            }
            System.out.println("─".repeat(60));

        } catch (NullPointerException e) {
            System.out.println("\nUnbekannter Befehl: " + befehlString);
            System.out.println("Nutze einen der verfügbaren Befehle oder ENDE zum Beenden.");
        }
    }



    public static void main(String[] args) {
        InteraktionsschnittstelleNeu interaktion = new InteraktionsschnittstelleNeu();
        interaktion.starte();
    }
}