package flo.jasmin.projekt.domain.Exceptions;

public class LaufGegenBarriereException extends Exception {
    public LaufGegenBarriereException() {
        super("Spielzug in diese Richtung nicht erlaubt");
    }
}
