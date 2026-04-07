package flo.jasmin.projekt.domain.Akteure;

import flo.jasmin.projekt.domain.Gegenstaende.Zutat;
import flo.jasmin.projekt.domain.Gegenstaende.konkreteZutaten.Karotte;
import flo.jasmin.projekt.domain.Gegenstaende.konkreteZutaten.Tomate;
import flo.jasmin.projekt.domain.Inventar;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private ArrayList<TeamWesen> wesenInTeam;
    private Inventar inventar;

    public Team(){
        wesenInTeam = new ArrayList<>(
                List.of(new TeamWesen(20, 0, 5, 5, 0, "Margaret"),
                        new TeamWesen(10, 0, 10, 2, 0, "Johann"))
        );
        inventar = new Inventar();

    }

    public ArrayList<TeamWesen> getWesenInTeam() {
        return wesenInTeam;
    }

    public void setWesenInTeam(ArrayList<TeamWesen> wesenInTeam) {
        this.wesenInTeam = wesenInTeam;
    }

    public List<TeamWesen> holeKampffähigeWesen(){
        return wesenInTeam.stream().filter(wesen -> wesen.kampfFähig()).toList();
    }

    public String heile(int lebensPunkte){
        for (TeamWesen teamWesen: wesenInTeam){
            //TODO: Lebenspunkteverlust wegen Floor
            teamWesen.heile(lebensPunkte / wesenInTeam.size());
        }
        return "Ihr habt jetzt " + lebensPunkte + " mehr Leben!";
    }

    public Inventar getInventar() {
        return inventar;
    }

    public void setInventar(Inventar inventar) {
        this.inventar = inventar;
    }
}