package es.safareyes.swapclosetmarlo.repositorios;

import es.safareyes.swapclosetmarlo.modelos.Solicitudes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ISolicitudesRepository extends JpaRepository<Solicitudes,Integer> {

    @Query("""
        SELECT s
        FROM Solicitudes s
        WHERE s.emisor.id = :idPerfil
    """)
    List<Solicitudes> buscarSolicitudesEnviadas(
            @Param("idPerfil") Integer idPerfil
    );

    @Query("""
        SELECT s
        FROM Solicitudes s
        WHERE s.receptor.id = :idPerfil
    """)
    List<Solicitudes> buscarSolicitudesRecibidas(
            @Param("idPerfil") Integer idPerfil
    );

}
