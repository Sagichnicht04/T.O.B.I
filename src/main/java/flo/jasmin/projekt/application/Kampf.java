package flo.jasmin.projekt.application;

import java.util.ArrayList;
import java.util.Comparator;

import flo.jasmin.projekt.domain.Akteure.Gegner;
import flo.jasmin.projekt.domain.Akteure.TeamWesen;
import flo.jasmin.projekt.domain.Akteure.Wesen;
import flo.jasmin.projekt.domain.Exceptions.ZielIstSpielerWesen;
import flo.jasmin.projekt.domain.Befehl;
import flo.jasmin.projekt.domain.Gegenstaende.Gegenstand;

public class Kampf {
    private ArrayList<Wesen> alleWesen;
    private int momentanesWesenIndex;
    private boolean kampfImGange;
    private ArrayList<Gegenstand> verloreneGegenstände;

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
        verloreneGegenstände = new ArrayList<>();
    }

    public ArrayList<Gegenstand> getVerloreneGegenstände() {
        return verloreneGegenstände;
    }

    public void setVerloreneGegenstände(ArrayList<Gegenstand> verloreneGegenstände) {
        this.verloreneGegenstände = verloreneGegenstände;
    }

    private void fügeVerlorenenGegenstandHinzu(Gegenstand gegenstand){
        this.verloreneGegenstände.add(gegenstand);
    }
    private void fügeVerlorenenGegenständeHinzu(ArrayList<Gegenstand> gegenstände){
        this.verloreneGegenstände.addAll(gegenstände);
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
        ziel.nehmeSchaden(getMomentanesWesen().getAngriff());

        ArrayList<String> antwort = new ArrayList<String>();
        antwort.add(ziel.getName() + " nimmt " + getMomentanesWesen().getAngriff() + " Schaden. HP übrig: "+ziel.getGesundheit());

        if(!ziel.kampfFähig()){
            antwort.add(ziel.getName() + " fällt zu Boden.");
            // Es fühlt sich so an, als hätte ich diesen Cast schon oft gemacht. Refactoren?
            if(ziel instanceof Gegner){
                ArrayList<Gegenstand> gegenstände = ((Gegner) ziel).getInventar();
                for(Gegenstand gegenstand: gegenstände){
                    antwort.add(ziel.getName() + " lässt " + gegenstand.getName() + " fallen.");
                    fügeVerlorenenGegenstandHinzu(gegenstand);
                }
            }
            entferneWesenAusListe(ziel);
        }

        erhöheMomentanesWesenIndex();
        if(!alleWesen.stream().filter(wesen -> wesen instanceof Gegner).toList().isEmpty()){
            antwort.addAll(gegnerGreiftAn());
        }else {
            kampfImGange = false;
            antwort.add("Du hast gewonnen. Alle Gegner wurden besiegt!");
        }
        return antwort;
    }


    public void entferneWesenAusListe(Wesen ziel){
        if(alleWesen.indexOf(ziel) < momentanesWesenIndex){
            momentanesWesenIndex -= 1;
        }
        alleWesen.remove(ziel);
    }


    //müsste Fehler schmeißen, wenn es kein passendens Wesen gibt
    public ArrayList<String> überMittelZiel(int ziel) throws IndexOutOfBoundsException, ZielIstSpielerWesen{
        return teamGreiftAn(alleWesen.get(ziel));
    }

    public ArrayList<String> gegnerGreiftAn(){
        ArrayList<String> antwort = new ArrayList<String>();
        while(getMomentanesWesen() instanceof Gegner && !alleWesen.stream().filter(wesen -> wesen instanceof TeamWesen).toList().isEmpty()) {
            Gegner angreifer = (Gegner) getMomentanesWesen();
            Wesen ziel = angreifer.ausgewähltesZiel(new ArrayList<>(alleWesen.stream().filter(wesen -> wesen instanceof TeamWesen).toList()));
            ziel.nehmeSchaden(angreifer.getAngriff());
            antwort.add(getMomentanesWesen().getName() + " greift an.");
            antwort.add(ziel.getName() + " nimmt " + angreifer.getAngriff() + " Schaden. HP übrig: "+ziel.getGesundheit());
            if(!ziel.kampfFähig()){
                entferneWesenAusListe(ziel);
            }
            erhöheMomentanesWesenIndex();
        }
        if (alleWesen.stream().filter(wesen -> wesen instanceof TeamWesen).toList().isEmpty()){
            antwort.add("Dein gesamtes Team wurde besiegt!");
            kampfImGange = false;
        } else {
            antwort.addAll(gibSpielerInfoÜberKampf());
        }
        return antwort;
    }

    public ArrayList<String> gibSpielerInfoÜberKampf(){
        ArrayList<String> antwort = new ArrayList<>();
        for (Wesen w: alleWesen){
            antwort.add(w.getName() +" : " + alleWesen.indexOf(w));
        }
        //ersetzt antwort.add("Dein Wesen: 'Wesen'");
        antwort.add(getMomentanesWesen().getName() + " ist an der Reihe");
        antwort.addAll(holeAlleGegner());
        return antwort;
    }

    private ArrayList<String> holeAlleGegner(){
        ArrayList<String> antwort = new ArrayList<>();
        antwort.add("Gegnerauswahl: ");
        for (Wesen g : alleWesen.stream().filter(w -> w.getClass().getSuperclass() == Gegner.class).toList()){
            antwort.add(g.getName() + " : " + alleWesen.indexOf(g) + "\n");
        }
        return antwort;
    }

}
