package es.safareyes.swapclosetmarlo.dtos;


import lombok.Data;

import java.sql.Timestamp;

@Data
public class PerfilRequestDTO {



    private String apellido1;

    private String apellido2;

    private String ciudad;

    private String nombre;

    private Timestamp fechanacimiento;

    private String talla;

    private String email;

}
