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


    //TODO: Heilwert zurückgeben
    public Map<Zutat, Integer> errechneMöglicheZutaten(Inventar inventar){
        Map<Zutat, Integer> ergebnis = new HashMap<>();
        for (Gegenstand gegenstand: inventar.getGegenstände()){
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
    }

    public String stringRepräsentationVonZutaten(Inventar inventar){
        Map<Zutat, Integer> zutaten = errechneMöglicheZutaten(inventar);
        String rückgabeWert = "";
        for(Map.Entry<Zutat, Integer> paar : zutaten.entrySet()){
            rückgabeWert += "\n" + paar.getKey().getName() + " : " +paar.getValue();
        }
        return rückgabeWert;
    }


//warum in mehrere Methoden unterteilt? Kochsystem sollte nicht die Items aus dem Inventar entfernen können
    public int errechneGesundheit(Map<Zutat, Integer> zutaten, Inventar inventar) throws FalscheZutatenEingabe {
        int ergebnis = 0;
        Map<Zutat, Integer> möglicheZutaten = errechneMöglicheZutaten(inventar);
        for (Zutat z : zutaten.keySet()){
            if(zutaten.get(z) <= möglicheZutaten.get(z)){
                    ergebnis += z.getHeilwert() * zutaten.get(z);
            } else{
                throw new FalscheZutatenEingabe();
            }
        }
        return ergebnis;
    }

    public Map<Zutat, Integer> übersetzteZutatenNameZuZutatObjekt(Map<String, Integer> zutaten, Inventar inventar) throws FalscheZutatenEingabe{
        Map<Zutat, Integer> übersetzung = new HashMap<Zutat, Integer>();
        for(String z : zutaten.keySet()){
            übersetzung.put(gibZutat(z, errechneMöglicheZutaten(inventar).keySet()), zutaten.get(z));
        }
        return übersetzung;
    }

    private Zutat gibZutat(String zutatName, Set<Zutat> möglicheZutaten) throws FalscheZutatenEingabe{
        for (Zutat z : möglicheZutaten){
            if (z.getName().toLowerCase().equals(zutatName.toLowerCase())){
                return z;
            }
        }
        throw new FalscheZutatenEingabe();
    }
}