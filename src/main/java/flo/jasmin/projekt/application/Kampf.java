package flo.jasmin.projekt.application;

import java.util.ArrayList;
import java.util.Comparator;

import flo.jasmin.projekt.domain.Akteure.Gegner;
import flo.jasmin.projekt.domain.Akteure.TeamWesen;
import flo.jasmin.projekt.domain.Akteure.Wesen;
import flo.jasmin.projekt.domain.Befehl;

public class Kampf {
    private ArrayList<Wesen> alleWesen;
    private int momentanesWesenIndex;

    public Kampf(ArrayList<Wesen> alleWesen){
        this.alleWesen = alleWesen;
        alleWesen.sort(Comparator.comparingInt(Wesen::getInitiative).reversed());
        momentanesWesenIndex = 0;
        System.out.println("DEBUG:" + alleWesen.get(momentanesWesenIndex).getName());
    }

    public ArrayList<Wesen> getAlleWesen() {
        return alleWesen;
    }

    public void setAlleWesen(ArrayList<Wesen> alleWesen) {
        this.alleWesen = alleWesen;
    }

    public int getMomentanesWesenIndex() {
        return momentanesWesenIndex;
    }

    public void setMomentanesWesenIndex(int momentanesWesenIndex) {
        this.momentanesWesenIndex = momentanesWesenIndex;
    }

    //Kapmfschritt wird benötigt weil wir keine While Loop haben können
    public void kampfSchritt(){
        if (getMomentanesWesen().getClass() == Gegner.class){
            gegnerGreiftAn();
            kampfSchritt();
        } else{
            //spieler darf angreifen
        }
    }

    public void erhöheMomentanesWesenIndex(){
        momentanesWesenIndex = (momentanesWesenIndex + 1) % alleWesen.size();
    }
    
    public Wesen getMomentanesWesen(){
        return alleWesen.get(momentanesWesenIndex);
    }

    public void teamGreiftAn(Wesen ziel){
        ziel.nehmeSchaden(getMomentanesWesen().getAngriff());
        erhöheMomentanesWesenIndex();
        System.out.println(ziel.getName() + " nimmt " + getMomentanesWesen().getAngriff() + " Schaden");
    }

    public void gegnerGreiftAn(){
        System.out.println(getMomentanesWesen().getClass());
        while(getMomentanesWesen().getClass().getSuperclass() == Gegner.class) {
            Gegner angreifer = (Gegner) getMomentanesWesen();

            Wesen ziel = angreifer.ausgewähltesZiel(new ArrayList<>(alleWesen.stream().filter(wesen -> wesen.getClass() == TeamWesen.class).toList()));
            ziel.nehmeSchaden(angreifer.getAngriff());
            System.out.println(ziel.getName() + " nimmt " + angreifer.getAngriff() + " Schaden");
            erhöheMomentanesWesenIndex();
        }
    }


    public void spieleBefehl(Befehl befehl, String parameter) {
        Wesen ziel = alleWesen.get(Integer.parseInt(parameter));
        teamGreiftAn(ziel);
        gegnerGreiftAn();
    }
}
