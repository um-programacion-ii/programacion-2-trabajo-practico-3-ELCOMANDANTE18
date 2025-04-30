

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SistemaPrestamos {
    private Catalogo catalogo;
    private List<Prestamo> prestamosActivos;

    public SistemaPrestamos(Catalogo catalogo) {
        this.catalogo = catalogo;
        this.prestamosActivos = new ArrayList<>();
    }

    public Prestamo prestarLibro(String isbn) {
        Libro libro = catalogo.buscarLibroPorISBN(isbn);
        if (libro != null && libro.getEstado() == Estado.DISPONIBLE) {
            libro.setEstado(Estado.PRESTADO);
            LocalDate fechaPrestamo = LocalDate.now();
            Prestamo prestamo = new Prestamo(fechaPrestamo, libro);
            prestamosActivos.add(prestamo);
            return prestamo;
        }
        return null; // Libro no encontrado o no disponible
    }

    public List<Prestamo> obtenerPrestamosActivos() {
        return new ArrayList<>(prestamosActivos); // Devolvemos una copia para evitar modificaciones externas
    }
}