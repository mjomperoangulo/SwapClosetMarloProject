package es.safareyes.swapclosetmarlo.modelos;

import jakarta.persistence.*;

@Entity
@Table(name = "prenda" ,schema = "swapcloset")
public class Prenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "estado")
    private estado estado;

    @Column(name = "talla")
    private String talla;

    @Column(name = "marca")
    private String marca;

    @Column(name = "tipo")
    private String tipo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_perfil",referencedColumnName = "id")
    private Perfil perfil;





}
