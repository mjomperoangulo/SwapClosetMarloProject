
package es.safareyes.swapclosetmarlo.errores;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PerfilNoEncontrado.class)
    public ResponseEntity<ErrorResponse> handleNoEncontrado(
            PerfilNoEncontrado ex,
            HttpServletRequest request) {

        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Recurso no encontrado",
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error);
    }


    // Error de categoría no encontrada
    @ExceptionHandler(CategoriaNoEncontrada.class)
    public ResponseEntity<ErrorResponse> handleCategoriaNoEncontrada(
            CategoriaNoEncontrada ex,
            HttpServletRequest request) {

        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Categoría no encontrada",
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error);
    }

    // Error de mensaje no encontrado
    @ExceptionHandler(MensajesNoEncontrado.class)
    public ResponseEntity<ErrorResponse> handleMensajesNoEncontrado(
            MensajesNoEncontrado ex,
            HttpServletRequest request) {

        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Mensaje no encontrado",
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }



    //Error de Prenda
    @ExceptionHandler(PrendaNoEncontrada.class)
    public ResponseEntity<ErrorResponse> handlePrendaNoEncontrada(
            PrendaNoEncontrada ex,
            HttpServletRequest request) {

        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Prenda no encontrada",
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }


    @ExceptionHandler(SolicitudesNoEncontrada.class)
    public ResponseEntity<ErrorResponse> handleSolicitudesNoEncontrada(
            SolicitudesNoEncontrada ex,
            HttpServletRequest request) {

        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Solicitud no encontrada",
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(ValoracionesNoEncontrada.class)
    public ResponseEntity<ErrorResponse> manejarValoracionNoEncontrada(
            ValoracionesNoEncontrada ex,
            HttpServletRequest request) {

        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Valoración no encontrada",
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }


    @ExceptionHandler(UsuarioNoEncontrado.class)
    public ResponseEntity<ErrorResponse> manejarUsuarioNoEncontrado(
            UsuarioNoEncontrado ex,
            HttpServletRequest request) {

        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Usuario no encontrado",
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }


}
