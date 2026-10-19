package es.safareyes.swapclosetmarlo.dtos;

import es.safareyes.swapclosetmarlo.modelos.estadoSolicitud;
import lombok.Data;

import java.util.Date;

@Data
public class SolicitudesDTO {

    private Integer id;

    private Date fechainicio;

    private Date fechafin;


    private estadoSolicitud estadoSolicitud;

}
