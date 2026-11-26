package es.safareyes.swapclosetmarlo.errores;

public class SolicitudNoValida extends RuntimeException {
    public SolicitudNoValida(String message) {
        super(message);
    }
}
