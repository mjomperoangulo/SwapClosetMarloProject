package es.safareyes.swapclosetmarlo.dtos;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MensajesRequestDTO {
    @NotBlank(message = "El mensaje no puede estar vacio")
    @Size(min = 1,max = 1000, message = "El mensaje no puede pasarse de 255 caracteres")

    private String contenido;
}
