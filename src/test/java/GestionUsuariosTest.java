import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GestionUsuariosTest {

    private GestionUsuarios gestionUsuarios;
    // Mocks de posibles dependencias futuras (no utilizadas directamente ahora)
    private Object servicioRegistroMock;
    private Object repositorioUsuariosMock;

    @BeforeEach
    void setUp() {
        gestionUsuarios = new GestionUsuarios();
        servicioRegistroMock = Mockito.mock(Object.class);
        repositorioUsuariosMock = Mockito.mock(Object.class);
    }

    @Test
    void registrarUsuario_registroExitoso() {
        String nombre = "Juan Perez";
        Usuario nuevoUsuario = gestionUsuarios.registrarUsuario(nombre);
        assertNotNull(nuevoUsuario);
        assertEquals(nombre, nuevoUsuario.getNombre());
        // En la implementación actual, no hay interacciones con los mocks
        // pero en un futuro podríamos verificar interacciones aquí
    }

    @Test
    void registrarUsuario_simulacionDeExcepcion() {
        String nombre = "Error Usuario";
        // Simulamos que un futuro servicio de registro podría lanzar una excepción
        // when(servicioRegistroMock.registrar(nombre)).thenThrow(new RuntimeException("Error de registro"));

        // Actualmente, registrarUsuario no lanza excepciones, así que esta prueba
        // solo demuestra cómo se probaría el manejo de excepciones si existieran.
        // Si en el futuro se añade manejo de excepciones, esta prueba se adaptaría.
        assertDoesNotThrow(() -> gestionUsuarios.registrarUsuario(nombre));
    }

    @Test
    void obtenerUsuarioPorNombre_usuarioEncontrado() {
        String nombre = "Maria Lopez";
        gestionUsuarios.registrarUsuario(nombre);
        Usuario usuarioEncontrado = gestionUsuarios.obtenerUsuarioPorNombre(nombre);
        assertNotNull(usuarioEncontrado);
        assertEquals(nombre, usuarioEncontrado.getNombre());
        // En la implementación actual, no hay interacciones con los mocks
    }

    @Test
    void obtenerUsuarioPorNombre_usuarioNoEncontrado() {
        String nombre = "Carlos Gomez";
        Usuario usuarioNoEncontrado = gestionUsuarios.obtenerUsuarioPorNombre(nombre);
        assertNull(usuarioNoEncontrado);
        // En la implementación actual, no hay interacciones con los mocks
    }
}