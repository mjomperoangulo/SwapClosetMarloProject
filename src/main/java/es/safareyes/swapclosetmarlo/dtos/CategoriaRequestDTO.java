package es.safareyes.swapclosetmarlo.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoriaRequestDTO {
    @NotBlank(message = "El nombre de categoria no puede estar vacio")
    @Size(max = 20, message = "El nombre no puede pasarse de 20 caracteres")
    private String nombre;
}
