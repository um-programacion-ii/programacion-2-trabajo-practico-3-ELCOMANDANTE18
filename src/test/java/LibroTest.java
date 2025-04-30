

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LibroTest {

    @Test
    void crearLibro_atributosCorrectos() {
        Libro libro = new Libro("978-1234567890", "El Señor de los Anillos", "J.R.R. Tolkien");
        assertEquals("978-1234567890", libro.getIsbn());
        assertEquals("El Señor de los Anillos", libro.getTitulo());
        assertEquals("J.R.R. Tolkien", libro.getAutor());
        assertEquals(Estado.DISPONIBLE, libro.getEstado()); // El estado inicial debe ser DISPONIBLE
    }

    @Test
    void setEstado_cambiaEstadoCorrectamente() {
        Libro libro = new Libro("978-0987654321", "Cien años de soledad", "Gabriel García Márquez");
        libro.setEstado(Estado.PRESTADO);
        assertEquals(Estado.PRESTADO, libro.getEstado());
    }
}