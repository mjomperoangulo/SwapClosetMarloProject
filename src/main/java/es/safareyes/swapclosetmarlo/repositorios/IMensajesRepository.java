package es.safareyes.swapclosetmarlo.repositorios;

import es.safareyes.swapclosetmarlo.modelos.Mensajes;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.domain.Pageable;

public interface IMensajesRepository extends JpaRepository<Mensajes, Integer> {

    @Query("""
        SELECT DISTINCT m
        FROM Mensajes m
        JOIN m.emisores e
        JOIN m.receptores r
        WHERE EXISTS (
            SELECT s.id
            FROM Solicitudes s
            WHERE s.id = :idSolicitud
              AND (
                  (e.id = s.emisor.id AND r.id = s.receptor.id)
                  OR
                  (e.id = s.receptor.id AND r.id = s.emisor.id)
              )
        )
        """)
    Page<Mensajes> buscarMensajesPorSolicitud(
            @Param("idSolicitud") Integer idSolicitud,
            Pageable pageable
    );









}
