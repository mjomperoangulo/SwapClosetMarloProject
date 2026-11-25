package es.safareyes.swapclosetmarlo.modelos;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "categoria" ,schema = ("swapcloset"))
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor

public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name="nombre")
    private String nombre;

}
