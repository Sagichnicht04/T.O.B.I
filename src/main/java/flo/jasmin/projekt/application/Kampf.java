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

    public ArrayList<String> teamGreiftAn(Wesen ziel){
        ziel.nehmeSchaden(getMomentanesWesen().getAngriff());
        ArrayList<String> antwort = new ArrayList<String>();
        antwort.add(ziel.getName() + " nimmt " + getMomentanesWesen().getAngriff() + " Schaden. HP übrig: "+ziel.getGesundheit());
        erhöheMomentanesWesenIndex();
        antwort.addAll(gegnerGreiftAn());
        return antwort;
    }

    //müsste Fehler schmeißen, wenn es kein passendens Wesen gibt
    public ArrayList<String> überMittelZiel(int ziel){
        return teamGreiftAn(alleWesen.get(ziel));
    }

    public ArrayList<String> gegnerGreiftAn(){
        //System.out.println(getMomentanesWesen().getClass());
        ArrayList<String> antwort = new ArrayList<String>();
        while(getMomentanesWesen().getClass().getSuperclass() == Gegner.class) {
            Gegner angreifer = (Gegner) getMomentanesWesen();
            Wesen ziel = angreifer.ausgewähltesZiel(new ArrayList<>(alleWesen.stream().filter(wesen -> wesen.getClass() == TeamWesen.class).toList()));
            ziel.nehmeSchaden(angreifer.getAngriff());
            antwort.add(ziel.getName() + " nimmt " + angreifer.getAngriff() + " Schaden. HP überig: "+ziel.getGesundheit());
            erhöheMomentanesWesenIndex();
        }
        antwort.addAll(gibSpielerInfoÜberKampf());
        return antwort;
    }

    public ArrayList<String> gibSpielerInfoÜberKampf(){
        ArrayList<String> antwort = new ArrayList<String>();
        for (Wesen w: alleWesen){
            antwort.add(w.getName() +" : " + alleWesen.indexOf(w));
        }
        antwort.add("Dein Wesen: 'Wesen'\nDie Gegner: '[alle Gegner]'");
        return antwort;
    }


    public void spieleBefehl(Befehl befehl, String parameter) {
        Wesen ziel = alleWesen.get(Integer.parseInt(parameter));
        teamGreiftAn(ziel);
        gegnerGreiftAn();
    }
}
