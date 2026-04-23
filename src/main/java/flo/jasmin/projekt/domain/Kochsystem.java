package flo.jasmin.projekt.domain;

import flo.jasmin.projekt.domain.Exceptions.FalscheZutatenEingabe;
import flo.jasmin.projekt.domain.Gegenstaende.Gegenstand;
import flo.jasmin.projekt.domain.Gegenstaende.Zutat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Kochsystem{


    //TODO: Heilwert zurueckgeben
/*     public Map<Zutat, Integer> errechneMoeglicheZutaten(Inventar inventar){
        Map<Zutat, Integer> ergebnis = new HashMap<>();
        for (Gegenstand gegenstand: inventar.getGegenstaende()){
            if(gegenstand.getClass().getSuperclass() == Zutat.class){
                if(ergebnis.containsKey(gegenstand)){
                    ergebnis.put((Zutat)gegenstand, ergebnis.get(gegenstand) + 1);
                }
                else {
                    ergebnis.put((Zutat)gegenstand, 1);
                }
            }
        }
        return ergebnis;
    } */

    public String stringRepraesentationVonZutaten(Map<Zutat, Integer> moeglicheZutaten){
        Map<Zutat, Integer> zutaten = moeglicheZutaten;
        String rueckgabeWert = "";
        for(Map.Entry<Zutat, Integer> paar : zutaten.entrySet()){
            rueckgabeWert += "\n" + paar.getKey().getName() + " : " +paar.getValue();
        }
        if (moeglicheZutaten.isEmpty()){
            return "Du hast noch keine Zutaten! Besgiege Gegner oder kaufe welche in Doerfern!";
        }
        return rueckgabeWert;
    }


//warum in mehrere Methoden unterteilt? Kochsystem sollte nicht die Items aus dem Inventar entfernen koennen
    public int errechneGesundheit(Map<Zutat, Integer> zutaten) throws FalscheZutatenEingabe {
        int ergebnis = 0;
        for (Zutat z : zutaten.keySet()){
            ergebnis += z.getHeilwert() * zutaten.get(z);
        }
        return ergebnis;
    }

    public Map<Zutat, Integer> uebersetzteZutatenNameZuZutatObjekt(Map<String, Integer> zutaten, Inventar inventar) throws FalscheZutatenEingabe{
        Map<Zutat, Integer> uebersetzung = new HashMap<Zutat, Integer>();
        for(String z : zutaten.keySet()){
            uebersetzung.put(gibZutat(z, inventar.getZutaten().keySet()), zutaten.get(z));
        }
        return uebersetzung;
    }

    private Zutat gibZutat(String zutatName, Set<Zutat> moeglicheZutaten) throws FalscheZutatenEingabe{
        for (Zutat z : moeglicheZutaten){
            if (z.getName().toLowerCase().equals(zutatName.toLowerCase())){
                return z;
            }
        }
        throw new FalscheZutatenEingabe();
    }
}