package es.safareyes.swapclosetmarlo.repositorios;

import es.safareyes.swapclosetmarlo.modelos.Solicitudes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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


    //Regla de negocio 4, para comprobar si existe una solicitud pendiente asociada a una prenda correcta.
    @Query(value = """
    SELECT EXISTS (
        SELECT 1
        FROM swapcloset.se_relaciona sr
        JOIN swapcloset.solicitudes s ON s.id = sr.id_solicitud
        WHERE sr.id_prenda = :idPrenda
        AND s.estado = 'Pendiente'
    )
    """, nativeQuery = true)
    boolean existeSolicitudPendiente(@Param("idPrenda") Integer idPrenda);


    //Otra query para insertar en la tabla intermedia el id de la prenda y solicitud (RN-04)
    @Modifying
    @Query(value = """
    INSERT INTO swapcloset.se_relaciona (id_prenda, id_solicitud)
    VALUES (:idPrenda, :idSolicitud)
    """, nativeQuery = true)
    void relacionarPrenda(
            @Param("idPrenda") Integer idPrenda,
            @Param("idSolicitud") Integer idSolicitud
    );



    //Query para cancelar solicitudes pendientes (RN-10)
    @Modifying
    @Query(value = """
    UPDATE swapcloset.solicitudes s
    SET estado = 'Cancelada'
    WHERE s.estado = 'Pendiente'
    AND s.id IN (
        SELECT sr.id_solicitud
        FROM swapcloset.se_relaciona sr
        WHERE sr.id_prenda = :idPrenda
    )
    """, nativeQuery = true)
    void cancelarSolicitudesPendientes(@Param("idPrenda") Integer idPrenda);




    // Contar todos los prestamos activos de un perfil como solicitante (RN-05)
    @Query(value = """
    SELECT COUNT(*)
    FROM swapcloset.solicitudes s
    JOIN swapcloset.se_relaciona sr ON sr.id_solicitud = s.id
    JOIN swapcloset.prenda p ON p.id = sr.id_prenda
    WHERE s.id_perfil_emisor = :idPerfil
    AND p.id_perfil = s.id_perfil_receptor
    AND p.tipo = 2
    AND s.estado = 'Aceptado'
    AND s.fechafin >= CURRENT_DATE
    """, nativeQuery = true)
    Integer contarPrestamosActivos(@Param("idPerfil") Integer idPerfil);















}
