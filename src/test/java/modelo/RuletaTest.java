package modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RuletaTest {
    private Ruleta ruleta;

    @BeforeEach
    void setUp() {
        ruleta = new Ruleta(1000);
    }

    //CAS0 1
    @Test
    void testConstructorSaldoNegativo() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Ruleta(-100);
        });
        assertEquals("Saldo inicial negativo", exception.getMessage());
    }

    //CAS0 2
    @Test
    void testDepositar() {
        int saldoInicial = ruleta.getSaldo();
        ruleta.depositar(500);
        assertEquals(saldoInicial + 500, ruleta.getSaldo());
    }

    //CAS0 3
    @Test
    void testJugarApuestaNula() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ruleta.jugar(null);
        });
        assertEquals("Apuesta obligatoria", exception.getMessage());
    }

    //CAS0 4
    @Test
    void testJugarSaldoInsuficiente() {
        ApuestaBase apuestaGrande = new ApuestaRojo(5000);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ruleta.jugar(apuestaGrande);
        });
        assertEquals("Saldo insuficiente", exception.getMessage());
    }
}
