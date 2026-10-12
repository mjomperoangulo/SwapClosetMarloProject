package es.safareyes.swapclosetmarlo.modelos;


import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "fotoprenda" ,schema = "swapcloset")

public class FotoPrenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name="url")
    private String url;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_prenda",referencedColumnName = "id")
    private Prenda prenda;





}
