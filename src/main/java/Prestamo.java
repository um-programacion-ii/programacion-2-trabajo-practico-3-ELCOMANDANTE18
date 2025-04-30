import java.time.LocalDate;

public class Prestamo {
    private LocalDate fechaPrestamo;
    private Libro libro;

    public Prestamo(LocalDate fechaPrestamo, Libro libro) {
        this.fechaPrestamo = fechaPrestamo;
        this.libro = libro;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public Libro getLibro() {
        return libro;
    }
}