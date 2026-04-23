package flo.jasmin.projekt.domain.Doerfer;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import flo.jasmin.projekt.domain.Exceptions.FalscheZutatenEingabe;
import flo.jasmin.projekt.domain.Gegenstaende.Ausstattungen.Ausstattung;
import flo.jasmin.projekt.domain.Gegenstaende.Gegenstand;
import flo.jasmin.projekt.domain.Gegenstaende.Zutaten.Zutat;

public class Dorf {

    private String beschreibung;
    private Set<Gegenstand> sortiment;

    public Dorf(String beschreibung, Set<Gegenstand> sortiment) {
        this.beschreibung = beschreibung;
        this.sortiment = sortiment;
    }
  // ausgabe des Sortiments mit Preis. 

    public String sortimentAnzeigen(){
        String sortimentAnzeige = "Hier das Sortiment: \nZutaten:\n-------------------------\n";
        for( Gegenstand g : sortiment.stream().filter(g -> g instanceof Zutat).toList()){
            sortimentAnzeige += g.getName() + " Preis: " + g.getPreis() + "\n";
        }
        sortimentAnzeige += "Ausstattung:\n----------------\n";
        for( Gegenstand g : sortiment.stream().filter(g -> g instanceof Ausstattung).toList()){
            sortimentAnzeige += g.getName() + " Preis: " + g.getPreis() + "\n";
        }
        return sortimentAnzeige;
    }

    public Map<Gegenstand, Integer> uebersetzeNameZuGegenstand(Map<String, Integer> eingabe) throws FalscheZutatenEingabe{
        try {
                   Map<Gegenstand, Integer> ergebniss = new HashMap<Gegenstand,Integer>();
        for(String s : eingabe.keySet()){
            Gegenstand gegenstand = sortiment.stream().filter(g -> Objects.equals(g.getName().toLowerCase(), s.toLowerCase())).toList().get(0);
            ergebniss.put(gegenstand, eingabe.get(s));
        }
        return ergebniss;
        } catch (IndexOutOfBoundsException e) {
            throw new FalscheZutatenEingabe();
        }
 
    }

    public static int gesamtpreisBerechnen(Map<Gegenstand, Integer> einkauf) {
        int preis = 0;
        for (Gegenstand g : einkauf.keySet()){
            preis += g.getPreis() * einkauf.get(g);
        }
        return preis;
    }

    public static String preisVisualisierung(int preis){
        return "Der Gesamtpreis fuer deinen Einkauf betraegt: " + preis + "\nBestaetige deinen Einkauf mit JA oder breche ab mit NEIN";
    }

  
    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }


}
