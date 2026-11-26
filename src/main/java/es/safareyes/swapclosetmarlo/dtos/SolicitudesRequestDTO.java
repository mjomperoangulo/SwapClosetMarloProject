package es.safareyes.swapclosetmarlo.dtos;

import es.safareyes.swapclosetmarlo.modelos.estadoSolicitud;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.Date;

@Data
public class SolicitudesRequestDTO {

    private Date fechainicio;

    private Date fechafin;

    @Pattern(regexp = "Aceptado|Rechazado|Pendiente|Completada|Finalizada", message = "El estado de la solicitud no es válido")
    private estadoSolicitud estadoSolicitud;

    private Integer idPrendaOfrecida;

}
