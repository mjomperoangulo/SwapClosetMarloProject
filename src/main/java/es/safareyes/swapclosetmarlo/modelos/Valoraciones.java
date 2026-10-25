package es.safareyes.swapclosetmarlo.modelos;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Entity
@Getter
@Setter
@Table(name = "valoraciones" ,schema = ("swapcloset"))


public class Valoraciones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "comentario")
    private String comentario;
    @Column(name = "estrellitas")
    private Integer estrellitas;
    @Column(name = "fecha")
    private Date fecha;

    @Getter
    @OneToOne
    @JoinColumn(name = "id_solicitud")
    private Solicitudes solicitud;
}
