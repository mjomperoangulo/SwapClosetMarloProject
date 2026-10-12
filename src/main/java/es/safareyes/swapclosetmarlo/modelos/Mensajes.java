package es.safareyes.swapclosetmarlo.modelos;


import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="mensajes", schema = "swapcloset")

public class Mensajes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name="contenido")
    private String contenido;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "envia",
            schema = "swapcloset",
            joinColumns = @JoinColumn(name = "id_mensaje"),
            inverseJoinColumns = @JoinColumn(name = "id_perfil")
    )
    private Set<Perfil> emisores = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "recibe",
            schema = "swapcloset",
            joinColumns = @JoinColumn(name = "id_mensaje"),
            inverseJoinColumns = @JoinColumn(name = "id_perfil")
    )
    private Set<Perfil> receptores = new HashSet<>();





    @Override
    public String toString() {
        return "Mensajes{" +
                "id=" + id +
                ", contenido='" + contenido + '\'' +
                '}';
    }







}
