package es.safareyes.swapclosetmarlo.dtos;

import es.safareyes.swapclosetmarlo.modelos.Estado;
import jakarta.persistence.Column;
import lombok.Data;

import java.util.Date;

@Data
public class PrendaRequestDTO {

    private Estado estado;
    private String nombre;
    private String talla;
    private String marca;
    private Integer tipo;
    private Date fechaPublicacion;



}
