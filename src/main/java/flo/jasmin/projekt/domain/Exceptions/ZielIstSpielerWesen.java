package flo.jasmin.projekt.domain.Exceptions;

public class ZielIstSpielerWesen extends Exception{
    public ZielIstSpielerWesen() {
        super("Du darfst dein eigenes Team nicht angreifen!");
    }
}
