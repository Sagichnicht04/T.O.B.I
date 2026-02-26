package main.java.flo.jasmin.projekt.domain.Karte;

import java.util.Map;

public class Karte {
    Map<Integer, Zelle> positionen;
    int momentanePosition;


    public Karte(Map<Integer, Zelle> positionen, int momentanePosition) {
        this.positionen = positionen;
        this.momentanePosition = momentanePosition;
    }

    
    public Map<Integer, Zelle> getPositionen() {
        return positionen;
    }
    public void setPositionen(Map<Integer, Zelle> positionen) {
        this.positionen = positionen;
    }
    public int getMomentanePosition() {
        return momentanePosition;
    }
    public void setMomentanePosition(int momentanePosition) {
        this.momentanePosition = momentanePosition;
    }

    
}
