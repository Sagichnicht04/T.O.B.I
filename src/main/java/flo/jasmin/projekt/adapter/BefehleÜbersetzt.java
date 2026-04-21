package flo.jasmin.projekt.adapter;

import flo.jasmin.projekt.domain.Befehl;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class BefehleÜbersetzt {

    private static final Map<String, Befehl> stringBefehl = new HashMap<>();
    private static final Map<Befehl, String> befehlString = new HashMap<>();

    public static void fülleAuf(){
        stringBefehl.put("norden", Befehl.HOCH);
        befehlString.put(Befehl.HOCH, "norden");
        stringBefehl.put("westen", Befehl.LINKS);
        befehlString.put(Befehl.LINKS, "westen");
        stringBefehl.put("sueden", Befehl.RUNTER);
        stringBefehl.put("süden", Befehl.RUNTER);
        befehlString.put(Befehl.RUNTER, "süden");
        stringBefehl.put("osten", Befehl.RECHTS);
        befehlString.put(Befehl.RECHTS, "osten");
        stringBefehl.put("campen", Befehl.CAMPEN);
        befehlString.put(Befehl.CAMPEN, "campen");
        stringBefehl.put("kochen", Befehl.KOCHEN);
        befehlString.put(Befehl.KOCHEN, "kochen");
        stringBefehl.put("zurueck", Befehl.ZURÜCK);
        stringBefehl.put("zurück", Befehl.ZURÜCK);
        befehlString.put(Befehl.ZURÜCK, "zurück");
        stringBefehl.put("speichern", Befehl.SPEICHERN);
        befehlString.put(Befehl.SPEICHERN, "speichern");
        stringBefehl.put("angriff", Befehl.ANGRIFF);
        befehlString.put(Befehl.ANGRIFF, "angriff");
        stringBefehl.put("zutaten", Befehl.ZUTATEN);
        befehlString.put(Befehl.ZUTATEN, "zutaten");
        stringBefehl.put("ausstatten", Befehl.KREATURAUSSTATTEN);
        befehlString.put(Befehl.KREATURAUSSTATTEN, "ausstatten");
        stringBefehl.put("ausrüsten", Befehl.AUSRÜSTEN);
        stringBefehl.put("ausruesten", Befehl.AUSRÜSTEN);
        befehlString.put(Befehl.AUSRÜSTEN, "ausrüsten");
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
    }

    public static Befehl übersetze(String befehl){
        if(stringBefehl.isEmpty()){
            fülleAuf();
        }
        return stringBefehl.get(befehl.toLowerCase());
    }
    public static String übersetze(Befehl befehl){
        if(befehlString.isEmpty()){
            fülleAuf();
        }
        return befehlString.get(befehl).toUpperCase();
    }
}
