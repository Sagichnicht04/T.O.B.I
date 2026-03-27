package flo.jasmin.projekt.application;

import java.util.ArrayList;
import java.util.Comparator;

import flo.jasmin.projekt.domain.Akteure.Gegner;
import flo.jasmin.projekt.domain.Akteure.TeamWesen;
import flo.jasmin.projekt.domain.Akteure.Wesen;
import flo.jasmin.projekt.domain.Exceptions.ZielIstSpielerWesen;
import flo.jasmin.projekt.domain.Befehl;

public class Kampf {
    private ArrayList<Wesen> alleWesen;
    private int momentanesWesenIndex;
    private boolean kampfImGange;

    public boolean isKampfImGange() {
        return kampfImGange;
    }

    public void setKampfImGange(boolean kampfImGange) {
        this.kampfImGange = kampfImGange;
    }

    public Kampf(ArrayList<Wesen> alleWesen){
        this.alleWesen = alleWesen;
        alleWesen.sort(Comparator.comparingInt(Wesen::getInitiative).reversed());
        momentanesWesenIndex = 0;
        kampfImGange = true;
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
   /*  public void kampfSchritt(){
        if (getMomentanesWesen().getClass() == Gegner.class){
            gegnerGreiftAn();
            kampfSchritt();
        } else{
            //spieler darf angreifen
        }
    } */

    public void erhöheMomentanesWesenIndex(){
        momentanesWesenIndex = (momentanesWesenIndex + 1) % alleWesen.size();
    }
    
    public Wesen getMomentanesWesen(){
        return alleWesen.get(momentanesWesenIndex);
    }

    public ArrayList<String> teamGreiftAn(Wesen ziel) throws ZielIstSpielerWesen{
        if(ziel.getClass() == TeamWesen.class){
            throw new ZielIstSpielerWesen();
        }
        if(!ziel.nehmeSchaden(getMomentanesWesen().getAngriff())){
            System.out.println("Gegner entfertnt!");
            entferneWesenAusListe(ziel);
        };
        ArrayList<String> antwort = new ArrayList<String>();
        antwort.add(ziel.getName() + " nimmt " + getMomentanesWesen().getAngriff() + " Schaden. HP übrig: "+ziel.getGesundheit());
        erhöheMomentanesWesenIndex();
        if(!alleWesen.stream().filter(wesen -> wesen.getClass().getSuperclass() == Gegner.class).toList().isEmpty()){
            
            antwort.addAll(gegnerGreiftAn());
        }else {
            kampfImGange = false;
            antwort.add("Du hast gewonnen. Alle Gegner wurden besiegt!");
        }
        return antwort;
    }


    public void entferneWesenAusListe(Wesen ziel){
        if(momentanesWesenIndex>= alleWesen.indexOf(ziel)){
            momentanesWesenIndex -= 1;
            alleWesen.remove(ziel);
        }
    }
//check ob irgendein gegener noch drinnen ist


    //müsste Fehler schmeißen, wenn es kein passendens Wesen gibt
    public ArrayList<String> überMittelZiel(int ziel) throws IndexOutOfBoundsException, ZielIstSpielerWesen{
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


  /*   public void spieleBefehl(Befehl befehl, String parameter) {
        Wesen ziel = alleWesen.get(Integer.parseInt(parameter));
        teamGreiftAn(ziel);
        gegnerGreiftAn();
    } */
}
