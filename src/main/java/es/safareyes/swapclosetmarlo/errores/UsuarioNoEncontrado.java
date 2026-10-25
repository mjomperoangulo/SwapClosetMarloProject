package es.safareyes.swapclosetmarlo.errores;

public class UsuarioNoEncontrado extends RuntimeException {
    public UsuarioNoEncontrado(String mensaje) {
        super(mensaje);
    }
}
