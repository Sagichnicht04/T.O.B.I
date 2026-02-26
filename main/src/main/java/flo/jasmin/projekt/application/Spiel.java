package main.java.flo.jasmin.projekt.application;

import main.java.flo.jasmin.projekt.domain.Akteure.Team;
import main.java.flo.jasmin.projekt.domain.Karte.Karte;

public class Spiel {
    private Team team;
    private Persistenz persistenz;
    private Karte karte;
    private Kochsystem kochsystem;
    private Inventar inventar;

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Persistenz getPersistenz() {
        return persistenz;
    }

    public void setPersistenz(Persistenz persistenz) {
        this.persistenz = persistenz;
    }

    public Karte getKarte() {
        return karte;
    }

    public void setKarte(Karte karte) {
        this.karte = karte;
    }

    public Kochsystem getKochsystem() {
        return kochsystem;
    }

    public void setKochsystem(Kochsystem kochsystem) {
        this.kochsystem = kochsystem;
    }

    public Inventar getInventar() {
        return inventar;
    }

    public void setInventar(Inventar inventar) {
        this.inventar = inventar;
    }
}
