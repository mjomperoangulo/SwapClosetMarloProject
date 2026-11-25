package es.safareyes.swapclosetmarlo.dtos;


import jakarta.validation.constraints.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class PerfilRequestDTO {


    @NotBlank(message = "El apellido no puede estar vacio")
    @Size(max = 20, message = "El apellido no puede pasarse de 20 caracteres")
    private String apellido1;
    @NotBlank(message = "El segundo apellido no puede estar vacio")
    @Size(max = 20, message = "El segundo apellido no puede pasarse de 20 caracteres")
    private String apellido2;

    @NotBlank(message = "El nombre de ciudad no puede estar vacio")
    @Size(max = 20, message = "El nombre de ciudad no puede pasarse de 20 caracteres")
    private String ciudad;

    @NotBlank(message = "El nombre no puede estar vacio")
    @Size(max = 20, message = "El nombre no puede pasarse de 20 caracteres")
    private String nombre;

    @FutureOrPresent(message = "la fecha de inicio no puede ser anterior a hoy")
    private Timestamp fechanacimiento;

    @NotBlank(message = "La talla no puede estar vacía")
    @Pattern(regexp = "XS|S|M|L|XL|XXL")
    private String talla;

    @Email(message = "El formato del email no es válido")
    @NotBlank(message = "El email no puede estar vacío")
    private String email;

}
