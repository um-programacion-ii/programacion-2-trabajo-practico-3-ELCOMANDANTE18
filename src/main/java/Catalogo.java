import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Catalogo {
    private Map<String, Libro> libros;

    public Catalogo() {
        this.libros = new HashMap<>();
    }

    public void agregarLibro(Libro libro) {
        this.libros.put(libro.getIsbn(), libro);
    }

    public Libro buscarLibroPorISBN(String isbn) {
        return this.libros.get(isbn);
    }

    public List<Libro> obtenerLibrosDisponibles() {
        List<Libro> disponibles = new ArrayList<>();
        for (Libro libro : this.libros.values()) {
            if (libro.getEstado() == Estado.DISPONIBLE) {
                disponibles.add(libro);
            }
        }
        return disponibles;
    }
}