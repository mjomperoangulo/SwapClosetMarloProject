
package es.safareyes.swapclosetmarlo.servicios;

import es.safareyes.swapclosetmarlo.dtos.SolicitudesRequestDTO;
import es.safareyes.swapclosetmarlo.errores.*;
import es.safareyes.swapclosetmarlo.mappers.SolicitudesMapper;
import es.safareyes.swapclosetmarlo.modelos.*;
import es.safareyes.swapclosetmarlo.repositorios.IPerfilRepository;
import es.safareyes.swapclosetmarlo.repositorios.IPrendaRepository;
import es.safareyes.swapclosetmarlo.repositorios.ISolicitudesRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class SolicitudesServicio {

    private final ISolicitudesRepository iSolicitudesRepository;
    private final SolicitudesMapper solicitudesMapper;
    private final IPrendaRepository iPrendaRepository;
    private final IPerfilRepository iPerfilRepository;

    public SolicitudesServicio(
            ISolicitudesRepository iSolicitudesRepository,
            SolicitudesMapper solicitudesMapper, IPrendaRepository iPrendaRepository, IPerfilRepository iPerfilRepository) {
        this.iSolicitudesRepository = iSolicitudesRepository;
        this.solicitudesMapper = solicitudesMapper;
        this.iPrendaRepository = iPrendaRepository;
        this.iPerfilRepository = iPerfilRepository;
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
    public SolicitudesRequestDTO create(Integer idPrenda,Integer idPerfil,SolicitudesRequestDTO dto) {
        Prenda prenda = iPrendaRepository.findById(idPrenda)
                .orElseThrow(() -> new PrendaNoEncontrada("No existe una prenda con id: " + idPrenda));

        Perfil perfil = iPerfilRepository.findById(idPerfil)
                .orElseThrow(() -> new PerfilNoEncontrado("No existe un perfil con id: " + idPerfil));

        //Comprobar que el estado tiene que ser disponible sino salta la excepcion
        if(prenda.getEstado() != Estado.disponible){
            throw new SolicitudNoValida("No se puede solicitar una prenda que no esta disponible");
        }

        //Comprobamos que no pueda solicitar su propia prenda
        if(prenda.getPerfil().getId().equals(perfil.getId())){
            throw new SolicitudNoValida("No puedes solicitar una prenda propia");
        }

        if(iSolicitudesRepository.existeSolicitudPendiente(idPrenda)){
            //Error 409 aplicacion
            throw new ConflictoSolicitud("Ya existe una solicitud que esta pendiente para esta prendwa");
        }



        Solicitudes solicitud = solicitudesMapper.toEntity(dto);

        solicitud.setEstadoSolicitud(estadoSolicitud.Pendiente);

        solicitud.setEmisor(perfil);
        solicitud.setReceptor(prenda.getPerfil());

        Solicitudes guardada = iSolicitudesRepository.save(solicitud);

        iSolicitudesRepository.relacionarPrenda(idPrenda, guardada.getId());


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
