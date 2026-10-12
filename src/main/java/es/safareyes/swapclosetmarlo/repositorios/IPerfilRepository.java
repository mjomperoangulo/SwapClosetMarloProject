package es.safareyes.swapclosetmarlo.repositorios;


import es.safareyes.swapclosetmarlo.modelos.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
public interface IPerfilRepository extends JpaRepository<Perfil, Integer> {


    @Query(value = """
        WITH estilos_del_perfil AS (
            SELECT id_estilos
            FROM swapcloset.identifica_con
            WHERE id_perfil = :idPerfil
        ),
        coincidencias AS (
            SELECT
                ic.id_perfil,
                COUNT(DISTINCT ic.id_estilos) AS estilos_comunes
            FROM swapcloset.identifica_con ic
            JOIN estilos_del_perfil ep
                ON ep.id_estilos = ic.id_estilos
            WHERE ic.id_perfil <> :idPerfil
            GROUP BY ic.id_perfil
        ),
        reputaciones AS (
            SELECT
                p.id AS id_perfil,
                AVG(v.estrellitas) AS reputacion_media
            FROM swapcloset.perfil p
            JOIN swapcloset.solicitudes s
                ON s.id = p.id_solicitud
            JOIN swapcloset.valoraciones v
                ON v.id_solicitud = s.id
            GROUP BY p.id
        )
        SELECT
            p.id,
            p.nombre,
            p.apellido1,
            c.estilos_comunes,
            ROUND(r.reputacion_media, 2) AS reputacion_media
        FROM coincidencias c
        JOIN swapcloset.perfil p
            ON p.id = c.id_perfil
        LEFT JOIN reputaciones r
            ON r.id_perfil = p.id
        ORDER BY
            c.estilos_comunes DESC,
            r.reputacion_media DESC NULLS LAST
        """, nativeQuery = true)
    List<PerfilRecomendado> buscarPerfilesRecomendados(
            @Param("idPerfil") Integer idPerfil);




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