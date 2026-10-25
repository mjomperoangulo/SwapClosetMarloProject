package es.safareyes.swapclosetmarlo.errores;

public class PerfilNoEncontrado extends RuntimeException {
    public PerfilNoEncontrado(String mensaje) {
        super(mensaje);
    }
}
