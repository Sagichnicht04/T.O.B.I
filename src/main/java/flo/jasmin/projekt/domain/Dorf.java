package flo.jasmin.projekt.domain;

import java.util.Set;

import flo.jasmin.projekt.domain.Gegenstaende.Gegenstand;

public class Dorf {

    private String beschreibung;
    private Set<Gegenstand> sortiment;

    public Dorf(String beschreibung, Set<Gegenstand> sortiment) {
        this.beschreibung = beschreibung;
        this.sortiment = sortiment;
    }
  // ausgabe des Sortiments mit Preis. 




  
    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public Set<Gegenstand> getSortiment() {
        return sortiment;
    }

    public void setSortiment(Set<Gegenstand> sortiment) {
        this.sortiment = sortiment;
    }

  

    
    

}
