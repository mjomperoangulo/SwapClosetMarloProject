package es.safareyes.swapclosetmarlo.modelos;


import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Perfil" ,schema = ("swapcloset"))
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;


    @Column(name = "apellido1")
    private String apellido1;
    @Column(name = "apellido2")
    private String apellido2;
    @Column(name = "ciudad")
    private String ciudad;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "fechanacimiento")
    private Timestamp fechanacimiento;
    @Column(name = "Talla")
    private String Talla;
    @Column(name = "Email")
    private String Email;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "identifica_con", schema = "swapcloset", joinColumns = @JoinColumn(name = "id_perfil"),
    inverseJoinColumns = @JoinColumn(name = "id_estilos")
    )
    private Set<Estilos> estilos = new HashSet<>();







//select e.id_tipo_organismo, count(*) from especie e
    //group by e.id_tipo_organismo ;


//Asi mejor
// select cto.nombre, count(e.*) from especie e
// join cat_tipo:organismo cto on cto.id = e.id_tipo_organistmo
// group by cto.nombre;


    //Ahora si queremos pasar esta consulta a java seria asi:
    //@Query(nativeQuery = true, value = "select cto.nombre as tipoOrganismo, count(e.*) as recuento
    // from especie e join cat_tipo:organismo cto on cto.id = e.id_tipo_organistmo
            // group by cto.nombre")
    //List<EjemploQueryDB> numeroDeEspeciesPorOrganismo


    //Ahora te irias a uno de los tests y harias lo siguiente :

    //@Test
    //void consultarEspecies() {
    //
    // List<EjemploQueryDB> todos ) especieRepository.numeroDeEspeciesPorOrganismo();

    //}































    //package es.safareyes.swapclosetmarlo.modelos;
//
//
//import jakarta.persistence.*;
//import lombok.*;
//
//@Entity
//@Table(name = "identifica_con")
//@Getter
//@Setter
//@EqualsAndHashCode
//@NoArgsConstructor
//@ToString
//
//public class identifica_con {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private Integer id;
//
//
//    //Esto esta mal pero es un ejemplo del profesor seria ManyToMany
//    @ManyToOne(fetch=FetchType.LAZY)//
//    @JoinColumn(name ="Estilos")
//    private Estilos estilos;
//    private//


//Que se hace en manytomany, en uno de los dos estilos o perfil pongo:

    //@ManyToMany(fetch = FetchType.LAZY)//
    //@JoinTable(
    //name = "identifica_con",
    //joinColumns= @JoinColumn(name = "id_perfil"),
    //inverseJoinColumns @JoinColumn(name = "id_Estilos")
    //)//


    //private List<Estilos>estilos;





//}//


}
