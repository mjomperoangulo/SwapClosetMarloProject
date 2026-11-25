package es.safareyes.swapclosetmarlo.modelos;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "solicitudes" ,schema = ("swapcloset"))


public class Solicitudes {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "fechainicio")
    private Date fechainicio;
    @Column(name = "fechafin")
    private Date fechafin;
    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private estadoSolicitud estadoSolicitud;

    @Getter
    @OneToOne(mappedBy = "solicitud")
    private Valoraciones valoracion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_perfil_emisor")
    private Perfil emisor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_perfil_receptor")
    private Perfil receptor;










    @Override
    public String toString() {
        return "Solicitudes{" +
                "id=" + id +
                ", estadoSolicitud=" + estadoSolicitud +
                ", fechainicio=" + fechainicio +
                ", fechafin=" + fechafin +
                '}';
    }

}
