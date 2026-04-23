package flo.jasmin.projekt.adapter;

import flo.jasmin.projekt.domain.Befehl;

import java.util.HashMap;
import java.util.Map;

public class BefehleUebersetzt {

    private static final Map<String, Befehl> stringBefehl = new HashMap<>();
    private static final Map<Befehl, String> befehlString = new HashMap<>();

    public static void fuelleAuf(){
        stringBefehl.put("norden", Befehl.HOCH);
        befehlString.put(Befehl.HOCH, "norden");
        stringBefehl.put("westen", Befehl.LINKS);
        befehlString.put(Befehl.LINKS, "westen");
        stringBefehl.put("sueden", Befehl.RUNTER);
        befehlString.put(Befehl.RUNTER, "sueden");
        stringBefehl.put("osten", Befehl.RECHTS);
        befehlString.put(Befehl.RECHTS, "osten");
        stringBefehl.put("campen", Befehl.CAMPEN);
        befehlString.put(Befehl.CAMPEN, "campen");
        stringBefehl.put("kochen", Befehl.KOCHEN);
        befehlString.put(Befehl.KOCHEN, "kochen");
        stringBefehl.put("zurueck", Befehl.ZURUeCK);
        befehlString.put(Befehl.ZURUeCK, "zurueck");
        stringBefehl.put("speichern", Befehl.SPEICHERN);
        befehlString.put(Befehl.SPEICHERN, "speichern");
        stringBefehl.put("angriff", Befehl.ANGRIFF);
        befehlString.put(Befehl.ANGRIFF, "angriff");
        stringBefehl.put("zutaten", Befehl.ZUTATEN);
        befehlString.put(Befehl.ZUTATEN, "zutaten");
        stringBefehl.put("ausstatten", Befehl.KREATURAUSSTATTEN);
        befehlString.put(Befehl.KREATURAUSSTATTEN, "ausstatten");
        stringBefehl.put("ausruesten", Befehl.AUSRUeSTEN);
        befehlString.put(Befehl.AUSRUeSTEN, "ausruesten");
        stringBefehl.put("kaufen", Befehl.KAUFEN);
        befehlString.put(Befehl.KAUFEN, "kaufen");
        stringBefehl.put("ja", Befehl.JA);
        befehlString.put(Befehl.JA, "ja");
        stringBefehl.put("nein", Befehl.NEIN);
        befehlString.put(Befehl.NEIN, "nein");
        stringBefehl.put("hilfe", Befehl.HILFE);
        befehlString.put(Befehl.HILFE, "hilfe");
        stringBefehl.put("reden", Befehl.REDEN);
        befehlString.put(Befehl.REDEN, "reden");
        stringBefehl.put("rekrutieren", Befehl.REKRUTIEREN);
        befehlString.put(Befehl.REKRUTIEREN, "rekrutieren");
    }

    public static Befehl uebersetze(String befehl){
        if(stringBefehl.isEmpty()){
            fuelleAuf();
        }
        return stringBefehl.get(befehl.toLowerCase());
    }
    public static String uebersetze(Befehl befehl){
        if(befehlString.isEmpty()){
            fuelleAuf();
        }
        return befehlString.get(befehl).toUpperCase();
    }
}
