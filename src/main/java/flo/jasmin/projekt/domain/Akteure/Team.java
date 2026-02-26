package flo.jasmin.projekt.domain.Akteure;

import java.util.ArrayList;

public class Team {
    private ArrayList<Wesen> wesenInTeam;

    public ArrayList<Wesen> getWesenInTeam() {
        return wesenInTeam;
    }

    public void setWesenInTeam(ArrayList<Wesen> wesenInTeam) {
        this.wesenInTeam = wesenInTeam;
    }
}