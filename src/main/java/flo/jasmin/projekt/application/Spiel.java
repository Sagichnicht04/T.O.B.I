package flo.jasmin.projekt.application;

import flo.jasmin.projekt.domain.Akteure.Team;
import flo.jasmin.projekt.domain.Befehl;
import flo.jasmin.projekt.domain.Exceptions.LaufGegenBarriereException;
import flo.jasmin.projekt.domain.Karte.Karte;
import flo.jasmin.projekt.domain.Status;

import java.util.ArrayList;
import java.util.Set;

public class Spiel {
    private Team team;
    private Karte karte;
    private Kochsystem kochsystem;
    private Status status;
    private Inventar inventar;

    public Spiel(){
        karte = new Karte();
        team = new Team();
        status = Status.EXISTIEREN;
        inventar = new Inventar();
    }

    //Holen wir vermutlich einfach nur aus dem Zellentypen
    public Set<Befehl> gibErlaubteBefehle(){
        return karte.gibMomentaneZelle().getZellentyp().getErlaubteBefehle();
    }

    public void spieleBefehl(Befehl befehl, String[] parameter){
        System.out.println("Du willst " + befehl.name());
        if(gibErlaubteBefehle().contains(befehl)){
            if(befehl == Befehl.RUNTER || befehl == Befehl.HOCH || befehl == Befehl.LINKS || befehl == Befehl.RECHTS){
                try {
                    karte.gehe(befehl);
                    System.out.println(karte.gibMomentaneZelle().getZellentyp().getBeschreibung());
                } catch (LaufGegenBarriereException e) {
                    System.out.println("How about we explore the area ahead of us later?");
                }
            }
        }
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
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
