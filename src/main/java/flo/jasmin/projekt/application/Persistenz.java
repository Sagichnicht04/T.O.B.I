package flo.jasmin.projekt.application;

public class Persistenz {
    String pfadZumSpeicherOrdner;

    public Persistenz(String pfadZumSpeicherOrdner) {
        this.pfadZumSpeicherOrdner = pfadZumSpeicherOrdner;
    }

    public void speichern(Spiel spiel){

    }

    public Spiel laden(){
        return new Spiel();
    }
    
}
