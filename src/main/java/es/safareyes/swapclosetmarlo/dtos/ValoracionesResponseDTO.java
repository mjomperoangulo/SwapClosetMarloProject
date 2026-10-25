package es.safareyes.swapclosetmarlo.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class ValoracionesResponseDTO {
    private Integer id;

    private String comentario;

    private Integer estrellitas;

    private Date fecha;
}
