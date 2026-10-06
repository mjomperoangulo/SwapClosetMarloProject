package es.safareyes.swapclosetmarlo.repositorios;


import es.safareyes.swapclosetmarlo.modelos.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
public interface IPerfilRepository extends JpaRepository<Perfil, Integer> {









}

//Usando Jpa Interfaz


//En una relacion la cual  la foreign key no esta en familia sino especies, como familia con especie (ej clase)
// @OneToMany(mappedBy = "familia", fetch= FetchType.LAZY) //y pones donde esta familia en la clase especie
//
// private List<Especie> especies;


//Usando JPQL (Mix Java + sql)

//@Repositoy
//public interface IEspecieRepositroy exntends ...

// @ Query ("select e from Especie e where e.tipoAgua.nombre = :nombreAgua ")
// List<Especie> buscarEspeciePorNombreTipoAgua(@Param("nombreAgua") String nombre);


//Otro metodo mas o otro tipo

//Query(value="select e.* from especie e\n" + "join cat_tipo agua cta on cta.id = e.id_tipo_agua \n" + "where cta.bombre = :agua ", nativeQuery = true)
//List<Continente> buscarPorAgua(@Param("agua") String nombreAgua);