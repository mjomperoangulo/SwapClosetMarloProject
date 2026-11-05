package es.safareyes.swapclosetmarlo.dtos;


import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Date;

@Data
public class ValoracionesRequestDTO {


    @NotBlank(message = "El comentario no puede estar vacio")
    @Size(max = 200, message = "El nombre no puede pasarse de 200 caracteres")
    private String comentario;

    @NotNull(message = "La puntuación es obligatoria")
    @Min(value = 1, message = "La puntuación mínima es 1")
    @Max(value = 5, message = "La puntuación máxima es 5")
    private Integer estrellitas;

    @FutureOrPresent(message = "la fecha no puede ser anterior a hoy")

    private Date fecha;

}
