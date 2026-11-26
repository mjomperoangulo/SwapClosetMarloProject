package es.safareyes.swapclosetmarlo.errores;

public class ConflictoSolicitud extends RuntimeException {
    public ConflictoSolicitud(String message) {
        super(message);
    }
}
