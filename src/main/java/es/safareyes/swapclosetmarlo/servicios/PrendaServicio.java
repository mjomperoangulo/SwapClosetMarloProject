
package es.safareyes.swapclosetmarlo.servicios;

import es.safareyes.swapclosetmarlo.dtos.PrendaRequestDTO;
import es.safareyes.swapclosetmarlo.errores.PrendaNoEncontrada;
import es.safareyes.swapclosetmarlo.mappers.PrendaMapper;
import es.safareyes.swapclosetmarlo.modelos.Categoria;
import es.safareyes.swapclosetmarlo.modelos.Estado;
import es.safareyes.swapclosetmarlo.modelos.Perfil;
import es.safareyes.swapclosetmarlo.modelos.Prenda;
import es.safareyes.swapclosetmarlo.repositorios.ICategoriaRepository;
import es.safareyes.swapclosetmarlo.repositorios.IPerfilRepository;
import es.safareyes.swapclosetmarlo.repositorios.IPrendaRepository;

import es.safareyes.swapclosetmarlo.repositorios.ISolicitudesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PrendaServicio {

    private final IPrendaRepository iPrendaRepository;
    private final IPerfilRepository iPerfilRepository;
    private final ICategoriaRepository iCategoriaRepository;
    private final PrendaMapper prendaMapper;
    private final ISolicitudesRepository iSolicitudesRepository;


    public PrendaServicio(
            IPrendaRepository iPrendaRepository, IPerfilRepository iPerfilRepository, ICategoriaRepository iCategoriaRepository,
            PrendaMapper prendaMapper, ISolicitudesRepository iSolicitudesRepository) {
        this.iPrendaRepository = iPrendaRepository;
        this.iPerfilRepository = iPerfilRepository;
        this.iCategoriaRepository = iCategoriaRepository;
        this.prendaMapper = prendaMapper;
        this.iSolicitudesRepository = iSolicitudesRepository;
    }

    // Obtener todas las prendas
    public List<PrendaRequestDTO> getAll() {
        return prendaMapper.toDtoList(
                iPrendaRepository.findByEstado(Estado.disponible)
        );
    }

    // Obtener una prenda por ID
    public PrendaRequestDTO getById(Integer id) {
        Prenda prenda = iPrendaRepository.findById(id)
                //Comprobacion de que no este retirada, para que no aparezca prendas retiradas
                .filter(p -> p.getEstado() != Estado.Retirada)
                .orElseThrow(() -> new PrendaNoEncontrada(
                        "No existe una prenda con ID: " + id
                ));

        return prendaMapper.toDTO(prenda);
    }

    // Crear una prenda
    @Transactional
    public PrendaRequestDTO create(PrendaRequestDTO dto, Integer idPerfil,Integer idCategoria) {
        Prenda prenda = prendaMapper.toEntity(dto);

        Perfil perfil = iPerfilRepository.findById(idPerfil)
                        .orElseThrow(() -> new RuntimeException("No existe ese perfil"));


        Categoria categoria = iCategoriaRepository.findById(idCategoria)
                        .orElseThrow(() -> new RuntimeException("No existe ese categoria"));

        prenda.setPerfil(perfil);
        prenda.setCategoria(categoria);

        prenda.setEstado(Estado.Borrador);


        Prenda guardada = iPrendaRepository.save(prenda);

        return prendaMapper.toDTO(guardada);
    }

    // Actualizar una prenda
    @Transactional
    public PrendaRequestDTO update(
            Integer id, PrendaRequestDTO dto) {

        Prenda prenda = iPrendaRepository.findById(id)
                .orElseThrow(() -> new PrendaNoEncontrada(
                        "No existe una prenda con ID: " + id
                ));

        prenda.setNombre(dto.getNombre());
        prenda.setTalla(dto.getTalla());
        prenda.setMarca(dto.getMarca());
        prenda.setTipo(dto.getTipo());
        prenda.setFechaPublicacion(dto.getFechaPublicacion());

        Prenda actualizada = iPrendaRepository.save(prenda);

        return prendaMapper.toDTO(actualizada);
    }

    // Eliminar una prenda RN-10
    @Transactional
    public void delete(Integer id) {
        Prenda prenda = iPrendaRepository.findById(id).orElseThrow(() -> new PrendaNoEncontrada("No existe una prenda con ID: " + id));

        prenda.setEstado(Estado.Retirada);

        iSolicitudesRepository.cancelarSolicitudesPendientes(id);

        iPrendaRepository.save(prenda);
    }











}
