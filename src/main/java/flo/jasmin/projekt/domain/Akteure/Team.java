package flo.jasmin.projekt.domain.Akteure;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private ArrayList<TeamWesen> wesenInTeam;

    public Team(){
        wesenInTeam = new ArrayList<>(List.of(new TeamWesen(20, 0, 5, 2, 0, "Margaret")));
    }

    public ArrayList<TeamWesen> getWesenInTeam() {
        return wesenInTeam;
    }

    public void setWesenInTeam(ArrayList<TeamWesen> wesenInTeam) {
        this.wesenInTeam = wesenInTeam;
    }

    public void heile(int lebensPunkte){
        for (TeamWesen teamWesen: wesenInTeam){
            //TODO: Lebenspunkteverlust wegen Floor
            teamWesen.heile(lebensPunkte / wesenInTeam.size());
        }
        System.out.println("Ihr habt jetz " + lebensPunkte + " mehr Leben!");
    }
}