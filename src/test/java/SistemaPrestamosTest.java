
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SistemaPrestamosTest {

    private Catalogo catalogoMock;
    private SistemaPrestamos sistemaPrestamos;
    private Libro libroDisponible;
    private Libro libroPrestado;

    @BeforeEach
    void setUp() {
        catalogoMock = Mockito.mock(Catalogo.class);
        sistemaPrestamos = new SistemaPrestamos(catalogoMock);

        libroDisponible = new Libro("123-456", "Libro Disponible", "Autor");
        libroPrestado = new Libro("789-012", "Libro Prestado", "Otro Autor");
        libroPrestado.setEstado(Estado.PRESTADO);
    }

    @Test
    void prestarLibro_libroDisponible_prestaCorrectamente() {
        when(catalogoMock.buscarLibroPorISBN("123-456")).thenReturn(libroDisponible);

        Prestamo prestamo = sistemaPrestamos.prestarLibro("123-456");

        assertNotNull(prestamo);
        assertEquals(libroDisponible, prestamo.getLibro());
        assertEquals(Estado.PRESTADO, libroDisponible.getEstado());
        assertEquals(LocalDate.now(), prestamo.getFechaPrestamo());
        verify(catalogoMock, times(1)).buscarLibroPorISBN("123-456");
    }

    @Test
    void prestarLibro_libroNoEncontrado_devuelveNull() {
        when(catalogoMock.buscarLibroPorISBN("999-999")).thenReturn(null);

        Prestamo prestamo = sistemaPrestamos.prestarLibro("999-999");

        assertNull(prestamo);
        verify(catalogoMock, times(1)).buscarLibroPorISBN("999-999");
        verify(catalogoMock, never()).agregarLibro(any()); // Aseguramos que no se intenta agregar un préstamo con libro nulo
    }

    @Test
    void prestarLibro_libroNoDisponible_devuelveNull() {
        when(catalogoMock.buscarLibroPorISBN("789-012")).thenReturn(libroPrestado);

        Prestamo prestamo = sistemaPrestamos.prestarLibro("789-012");

        assertNull(prestamo);
        verify(catalogoMock, times(1)).buscarLibroPorISBN("789-012");
    }

    @Test
    void obtenerPrestamosActivos_devuelveListaDePrestamos() {
        when(catalogoMock.buscarLibroPorISBN("123-456")).thenReturn(libroDisponible);
        Prestamo prestamo1 = sistemaPrestamos.prestarLibro("123-456");

        when(catalogoMock.buscarLibroPorISBN("otro-isbn")).thenReturn(new Libro("otro-isbn", "Otro Libro", "Otro Autor"));
        Prestamo prestamo2 = sistemaPrestamos.prestarLibro("otro-isbn");

        List<Prestamo> activos = sistemaPrestamos.obtenerPrestamosActivos();

        assertEquals(2, activos.size());
        assertTrue(activos.contains(prestamo1));
        assertTrue(activos.contains(prestamo2));
    }

    @Test
    void obtenerPrestamosActivos_noHayPrestamos_devuelveListaVacia() {
        List<Prestamo> activos = sistemaPrestamos.obtenerPrestamosActivos();
        assertTrue(activos.isEmpty());
    }
}