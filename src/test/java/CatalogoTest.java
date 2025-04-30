import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class CatalogoTest {

    private Catalogo catalogo;
    private Libro libro1;
    private Libro libro2;
    private Libro libroPrestado;

    @BeforeEach
    void setUp() {
        catalogo = new Catalogo();
        libro1 = new Libro("978-0321765723", "The Lord of the Rings", "J.R.R. Tolkien");
        libro2 = new Libro("978-0743273565", "The Great Gatsby", "F. Scott Fitzgerald");
        libroPrestado = new Libro("978-0061120084", "To Kill a Mockingbird", "Harper Lee");
        libroPrestado.setEstado(Estado.PRESTADO);
        catalogo.agregarLibro(libro1);
        catalogo.agregarLibro(libro2);
        catalogo.agregarLibro(libroPrestado);
    }

    @Test
    void agregarLibro_seAgregaCorrectamente() {
        Libro nuevoLibro = new Libro("978-1984801939", "Project Hail Mary", "Andy Weir");
        catalogo.agregarLibro(nuevoLibro);
        assertEquals(4, catalogo.obtenerCantidadDeLibros());
        assertNotNull(catalogo.buscarLibroPorISBN("978-1984801939"));
    }

    @Test
    void buscarLibroPorISBN_libroExistente_devuelveLibro() {
        Libro encontrado = catalogo.buscarLibroPorISBN("978-0321765723");
        assertNotNull(encontrado);
        assertEquals("The Lord of the Rings", encontrado.getTitulo());
    }

    @Test
    void buscarLibroPorISBN_libroNoExistente_devuelveNull() {
        Libro encontrado = catalogo.buscarLibroPorISBN("978-XXXXXXXXXXX");
        assertNull(encontrado);
    }

    @Test
    void obtenerLibrosDisponibles_multiplesLibros_devuelveSoloDisponibles() {
        List<Libro> disponibles = catalogo.obtenerLibrosDisponibles();
        assertEquals(2, disponibles.size());
        assertTrue(disponibles.contains(libro1));
        assertTrue(disponibles.contains(libro2));
        assertFalse(disponibles.contains(libroPrestado));
    }

    @Test
    void obtenerLibrosDisponibles_ningunLibroDisponible_devuelveListaVacia() {
        Catalogo catalogoSinDisponibles = new Catalogo();
        Libro libro1Prestado = new Libro("978-1111111111", "Libro 1", "Autor 1");
        libro1Prestado.setEstado(Estado.PRESTADO);
        Libro libro2Prestado = new Libro("978-2222222222", "Libro 2", "Autor 2");
        libro2Prestado.setEstado(Estado.PRESTADO);
        catalogoSinDisponibles.agregarLibro(libro1Prestado);
        catalogoSinDisponibles.agregarLibro(libro2Prestado);
        assertTrue(catalogoSinDisponibles.obtenerLibrosDisponibles().isEmpty());
    }
}