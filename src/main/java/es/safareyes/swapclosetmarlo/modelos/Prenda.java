package es.safareyes.swapclosetmarlo.modelos;

import es.safareyes.swapclosetmarlo.repositorios.IPrendaRepository;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "prenda" ,schema = "swapcloset")
public class Prenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private Estado estado;

    @Column(name = "nombre")
    private String nombre   ;

    @Column(name = "talla")
    private String talla;

    @Column(name = "marca")
    private String marca;

    @Column(name = "tipo")
    private Integer tipo;

    @Column(name = "fecha_publicacion")
    private Date fechaPublicacion;

    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_perfil",referencedColumnName = "id")
    private Perfil perfil;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria",referencedColumnName = "id")
    private Categoria categoria;

    @Getter
    @OneToMany(mappedBy = "prenda", fetch = FetchType.LAZY)
    private Set<FotoPrenda> fotos = new HashSet<>();




    @Override
    public String toString() {
        return "Prenda{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", estado='" + estado + '\'' +
                ", talla='" + talla + '\'' +
                ", marca='" + marca + '\'' +
                ", tipo=" + tipo +
                '}';
    }


    //metodo para guardar la ropa como borrador

    public Prenda publicarPrenda(Prenda prenda, IPrendaRepository repositorio) {
        prenda.setEstado(Estado.Borrador);
        return repositorio.save(prenda);
    }



}
