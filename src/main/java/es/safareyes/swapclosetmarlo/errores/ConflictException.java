package es.safareyes.swapclosetmarlo.errores;

public class ConflictException extends RuntimeException {

    public ConflictException(String mensaje){
        super(mensaje);
    }

}
