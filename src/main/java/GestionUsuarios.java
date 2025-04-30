import java.util.HashMap;
import java.util.Map;

public class GestionUsuarios {
    private Map<String, Usuario> usuariosRegistrados;

    public GestionUsuarios() {
        this.usuariosRegistrados = new HashMap<>();
    }

    public Usuario registrarUsuario(String nombre) {
        Usuario nuevoUsuario = new Usuario(nombre);
        this.usuariosRegistrados.put(nombre, nuevoUsuario);
        return nuevoUsuario;
    }

    public Usuario obtenerUsuarioPorNombre(String nombre) {
        return this.usuariosRegistrados.get(nombre);
    }
}