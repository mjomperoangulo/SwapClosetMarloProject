
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

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
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

        //Comprobacion de si es intercambio o prestamo y si no lo es manda una excepcion (RN-05)
        if(prenda.getTipo() != 1 && prenda.getTipo() !=2){
            throw new SolicitudNoValida("La modalidad de la prenda no admite este tipo de solicitud");
        }

        if(iSolicitudesRepository.existeSolicitudPendiente(idPrenda)){
            //Error 409 aplicacion
            throw new ConflictoSolicitud("Ya existe una solicitud que esta pendiente para esta prendwa");
        }


        //Si es prestamo (RN-05)
        if(prenda.getTipo() == 2){
            if(iSolicitudesRepository.contarPrestamosActivos(idPerfil) >= 3){
                throw new SolicitudNoValida("Has superado el maximo de 3 prestamos activos");
            }

            if(dto.getFechainicio() == null || dto.getFechafin() == null){
                throw new SolicitudNoValida("Las fechas son obligatorias en el prestamo");


            }


            LocalDate inicio = dto.getFechainicio().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            LocalDate fin = dto.getFechafin().toInstant()
                    .atZone(ZoneId.systemDefault()).toLocalDate();

            LocalDate hoy = LocalDate.now();

            if(inicio.isBefore(hoy)){
                throw new SolicitudNoValida("La fecha de inicio no puede ser anterior a hoy");
            }

            if(!fin.isAfter(inicio)){
                throw new SolicitudNoValida("La fecha de fin debe ser posterior al inicio");
            }

            if(ChronoUnit.DAYS.between(inicio,fin) > 30){
                throw new SolicitudNoValida("Un préstamo no puede pasar los 30 dias");
            }

        }
        //Si la prenda es intercambio (RN-05)
        if(prenda.getTipo() == 1){

            if(dto.getIdPrendaOfrecida() == null){
                throw new SolicitudNoValida("Debes indicar una prenda para ofrecer");
            }

            if(dto.getIdPrendaOfrecida().equals(idPrenda)){
                throw new SolicitudNoValida("No puedes ofrecer la misma prenda que estas solicitando");
            }

            Prenda prendaOfrecida = iPrendaRepository.findById(dto.getIdPrendaOfrecida()).orElseThrow(() -> new PrendaNoEncontrada("No existe la prenda ofrecida"));



            if(!prendaOfrecida.getPerfil().getId().equals(perfil.getId())){
                throw new SolicitudNoValida("La prenda ofrecida no la tiene el usuario");

            }

            if (iSolicitudesRepository.existeSolicitudPendiente(dto.getIdPrendaOfrecida())) {
                throw new ConflictoSolicitud("La prenda ofrecida ya tiene una solicitud pendiente");
            }

            if(prendaOfrecida.getEstado() != Estado.disponible){
                throw new SolicitudNoValida("La prenda ofrecida no esta disponible");
            }



        }




        Solicitudes solicitud = solicitudesMapper.toEntity(dto);

        solicitud.setEstadoSolicitud(estadoSolicitud.Pendiente);

        solicitud.setEmisor(perfil);
        solicitud.setReceptor(prenda.getPerfil());

        Solicitudes guardada = iSolicitudesRepository.save(solicitud);

        iSolicitudesRepository.relacionarPrenda(idPrenda, guardada.getId());
        if(prenda.getTipo() == 1){
            iSolicitudesRepository.relacionarPrenda(dto.getIdPrendaOfrecida(), guardada.getId());
        }


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
