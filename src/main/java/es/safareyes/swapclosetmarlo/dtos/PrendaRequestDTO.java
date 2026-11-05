package es.safareyes.swapclosetmarlo.dtos;

import es.safareyes.swapclosetmarlo.modelos.Estado;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Date;

@Data
public class PrendaRequestDTO {

    private Estado estado;
    private String nombre;


    @NotBlank(message = "La talla no puede estar vacía")
    @Pattern(regexp = "XS|S|M|L|XL|XXL")
    private String talla;

    @NotBlank(message = "La marca no puede estar vacía")
    @Size(max = 50, message = "La marca no puede superar los 50 caracteres")
    private String marca;

    @NotBlank(message = "El tipo no puede estar vacio")
    @Min(value = 1, message = "El tipo debe ser 1 (Intercambio) o 2 (Préstamo)")
    @Max(value = 2, message = "El tipo debe ser 1 (Intercambio) o 2 (Préstamo)")
    private Integer tipo;

    @FutureOrPresent(message = "la fecha de inicio no puede ser anterior a hoy")
    private Date fechaPublicacion;



}
