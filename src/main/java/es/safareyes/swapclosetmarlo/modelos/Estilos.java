package es.safareyes.swapclosetmarlo.modelos;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "estilos", schema = "swapcloset")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@ToString


public class Estilos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nombre")
    private String nombre;


}
