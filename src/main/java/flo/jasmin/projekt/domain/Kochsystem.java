package flo.jasmin.projekt.domain;

import flo.jasmin.projekt.domain.Exceptions.FalscheZutatenEingabe;
import flo.jasmin.projekt.domain.Gegenstaende.Gegenstand;
import flo.jasmin.projekt.domain.Gegenstaende.Zutat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Kochsystem{


    //TODO: Heilwert zurückgeben
    public Map<String, Integer> errechneMöglicheZutaten(Inventar inventar){
        Map<String, Integer> ergebnis = new HashMap<>();
        for (Gegenstand gegenstand: inventar.getGegenstände()){
            if(gegenstand.getClass() == Zutat.class){
                String name = gegenstand.getName();
                if(ergebnis.containsKey(name)){
                    ergebnis.put(name, ergebnis.get(name) + 1);
                }
                else {
                    ergebnis.put(name, 1);
                }
            }
        }
        return ergebnis;
    }
    public int errechneGesundheit(Map<String, Integer> zutaten, Inventar inventar) throws FalscheZutatenEingabe {
        int ergebnis = 0;
        Map<String, Integer> möglicheZutaten = errechneMöglicheZutaten(inventar);
        for(String zutat : zutaten.keySet()){
            if (möglicheZutaten.containsKey(zutat) && möglicheZutaten.get(zutat) >= zutaten.get(zutat)) {
                int heilwert = 0;
                for(Gegenstand gegenstand : inventar.getGegenstände()){
                    if(gegenstand.getClass() == Zutat.class && Objects.equals(gegenstand.getName(), zutat)) {
                        heilwert = ((Zutat) gegenstand).getHeilwert();
                    }
                }
                ergebnis += heilwert * zutaten.get(zutat);
            }
            else{
                throw new FalscheZutatenEingabe();
            }

        }
        return ergebnis;
    }
}