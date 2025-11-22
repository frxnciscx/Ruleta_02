package controlador;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SessionControllerTest {
    private SessionController session;

    @BeforeEach
    void setUp() {
        session = new SessionController();
        session.cerrarSesion();
    }

    //CASO 6
    @Test
    void testLoginUsuarioNoRegistrado() {
        boolean resultado = session.iniciarSesion("UsuarioMisterioso", "1234");
        assertFalse(resultado, "Acceso denegado");
    }

    //CASO 7
    @Test
    void testRegistroUsuarioNulo() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            session.registrarUsuario(null, "1234", "Nombre");
        });
        assertEquals("Datos de registro incompletos", exception.getMessage());
    }
}