package es.safareyes.swapclosetmarlo.modelos;


import jakarta.persistence.*;

@Entity
@Table(name = "categoria" ,schema = ("swapcloset"))

public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name="nombre")
    private String nombre;

}
