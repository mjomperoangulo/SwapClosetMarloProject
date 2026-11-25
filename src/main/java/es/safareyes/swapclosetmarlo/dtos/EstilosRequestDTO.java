package es.safareyes.swapclosetmarlo.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EstilosRequestDTO {
    @NotBlank(message = "El nombre de estilos no puede estar vacio")
    @Size(max = 20, message = "El nombre del estilo no puede pasarse de 20 caracteres")
    private String nombre;

}
