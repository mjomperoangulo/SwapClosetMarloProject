
package es.safareyes.swapclosetmarlo.servicios;

import es.safareyes.swapclosetmarlo.dtos.SolicitudesRequestDTO;
import es.safareyes.swapclosetmarlo.errores.SolicitudesNoEncontrada;
import es.safareyes.swapclosetmarlo.mappers.SolicitudesMapper;
import es.safareyes.swapclosetmarlo.modelos.Solicitudes;
import es.safareyes.swapclosetmarlo.repositorios.ISolicitudesRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class SolicitudesServicio {

    private final ISolicitudesRepository iSolicitudesRepository;
    private final SolicitudesMapper solicitudesMapper;

    public SolicitudesServicio(
            ISolicitudesRepository iSolicitudesRepository,
            SolicitudesMapper solicitudesMapper) {
        this.iSolicitudesRepository = iSolicitudesRepository;
        this.solicitudesMapper = solicitudesMapper;
    }

    // Obtener todas las solicitudes
    public List<SolicitudesRequestDTO> getAll() {
        return solicitudesMapper.toDtoList(
                iSolicitudesRepository.findAll()
        );
    }

    // Obtener una solicitud por ID
    public SolicitudesRequestDTO getById(Integer id) {
        Solicitudes solicitud = iSolicitudesRepository.findById(id)
                .orElseThrow(() -> new SolicitudesNoEncontrada(
                        "No existe una solicitud con ID: " + id
                ));

        return solicitudesMapper.toDto(solicitud);
    }

    // Crear una solicitud
    @Transactional
    public SolicitudesRequestDTO create(SolicitudesRequestDTO dto) {
        Solicitudes solicitud = solicitudesMapper.toEntity(dto);
        Solicitudes guardada = iSolicitudesRepository.save(solicitud);

        return solicitudesMapper.toDto(guardada);
    }

    // Actualizar una solicitud
    @Transactional
    public SolicitudesRequestDTO update(
            Integer id, SolicitudesRequestDTO dto) {

        Solicitudes solicitud = iSolicitudesRepository.findById(id)
                .orElseThrow(() -> new SolicitudesNoEncontrada(
                        "No existe una solicitud con ID: " + id
                ));

        solicitud.setFechainicio(dto.getFechainicio());
        solicitud.setFechafin(dto.getFechafin());
        solicitud.setEstadoSolicitud(dto.getEstadoSolicitud());

        Solicitudes actualizada =
                iSolicitudesRepository.save(solicitud);

        return solicitudesMapper.toDto(actualizada);
    }

    // Eliminar una solicitud
    @Transactional
    public void delete(Integer id) {
        if (!iSolicitudesRepository.existsById(id)) {
            throw new SolicitudesNoEncontrada(
                    "No existe una solicitud con ID: " + id
            );
        }

        iSolicitudesRepository.deleteById(id);
    }
}
