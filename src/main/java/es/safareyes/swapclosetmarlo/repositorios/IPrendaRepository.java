package es.safareyes.swapclosetmarlo.repositorios;

import es.safareyes.swapclosetmarlo.modelos.Categoria;
import es.safareyes.swapclosetmarlo.modelos.Estado;
import es.safareyes.swapclosetmarlo.modelos.Perfil;
import es.safareyes.swapclosetmarlo.modelos.Prenda;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Optional;

public interface IPrendaRepository extends JpaRepository<Prenda, Integer>, JpaSpecificationExecutor<Prenda> {

    List<Prenda> findByCategoria(Categoria categoria);
    List<Prenda>findByTipo(Integer tipo);
    List<Prenda>findByTalla(String talla);

    List<Prenda>findByEstado(Estado estado);

    Page<Prenda> findByPerfil_CiudadIgnoreCase(String ciudad, Pageable pageable);

    Optional<Prenda> findById(Integer id);   //Clase de java para decir si un resultado puede o no existir (Optimal)

    Page<Prenda> findByCategoria_Id(
            Integer idCategoria,
            Pageable pageable
    );

    Page<Prenda> findByTallaIgnoreCase(
            String talla,
            Pageable pageable
    );

    Page<Prenda> findByEstado(
            Estado estado,
            Pageable pageable
    );

    Page<Prenda> findByTipo(
            Integer tipo,
            Pageable pageable
    );

    Page<Prenda> findDistinctByPerfil_Estilos_Id(
            Integer idEstilo,
            Pageable pageable
    );


    //Segunda consulta
    @Query("""
           
                SELECT DISTINCT p
                FROM Prenda p
                LEFT JOIN FETCH p.fotos
                JOIN FETCH p.perfil perfil
                LEFT JOIN FETCH perfil.solicitudes solicitud
                LEFT JOIN FETCH solicitud.valoracion    
                WHERE p.id = :id
          
        """)
    Prenda findDetalleById(@Param("id") Integer id);
    //2 consulta
    Prenda findPrendaById(Integer id);


    //Tercera consulta

    @Query("""
    SELECT p.categoria.nombre as nombreCategoria, COUNT(p) as numeroPrendas
    FROM Prenda p
    WHERE p.estado = :estado
    GROUP BY p.categoria.id, p.categoria.nombre
    """)
    List<PrendasPorCategorias> contarPrendasDisponiblesPorCategoria(
            @Param("estado") Estado estado
    );
    List<Prenda> findByPerfil_Id(Integer idPerfil);






























}
