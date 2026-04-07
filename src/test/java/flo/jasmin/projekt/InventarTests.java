package flo.jasmin.projekt;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import flo.jasmin.projekt.domain.Inventar;
import flo.jasmin.projekt.domain.Exceptions.NichtGenugErsparrtes;

public class InventarTests {

    Inventar testInventar;

    @BeforeEach
    void setup(){
        testInventar = new Inventar();
        testInventar.setErspartes(5);
    }

    @Test
    void nichtGenugErsparrtesFehlerWennZuWenigGeld(){
        assertThrows(NichtGenugErsparrtes.class, () -> testInventar.geldEntfernen(6));
    }
}
