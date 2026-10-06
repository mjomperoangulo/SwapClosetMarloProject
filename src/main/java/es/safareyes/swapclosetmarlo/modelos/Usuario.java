package es.safareyes.swapclosetmarlo.modelos;


import jakarta.persistence.*;

@Entity
@Table(name = "usuario" ,schema = "swapcloset")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "rol")
    private String rol;

    @Column(name = "contraseña")
    private String contraseña;



    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_perfil",referencedColumnName = "id")
    private Perfil perfil;





}
