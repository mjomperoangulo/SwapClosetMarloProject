package es.safareyes.swapclosetmarlo.dtos;


import lombok.Data;

import java.sql.Timestamp;

@Data
public class PerfilDTO {

    private Integer id;



    private String apellido1;

    private String apellido2;

    private String ciudad;

    private String nombre;

    private Timestamp fechanacimiento;

    private String Talla;

    private String Email;

}
