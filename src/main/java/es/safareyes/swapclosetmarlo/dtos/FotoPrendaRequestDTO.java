package es.safareyes.swapclosetmarlo.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class FotoPrendaRequestDTO {
    @NotBlank(message = "El URL de FotoPrenda no puede estar vacio")
    @Size(max = 255, message = "La url no puede pasarse de 255 caracteres")
    private String url;

}
