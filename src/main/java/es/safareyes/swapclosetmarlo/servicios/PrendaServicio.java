
package es.safareyes.swapclosetmarlo.servicios;

import es.safareyes.swapclosetmarlo.dtos.PrendaRequestDTO;
import es.safareyes.swapclosetmarlo.errores.PrendaNoEncontrada;
import es.safareyes.swapclosetmarlo.mappers.PrendaMapper;
import es.safareyes.swapclosetmarlo.modelos.Prenda;
import es.safareyes.swapclosetmarlo.repositorios.IPrendaRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PrendaServicio {

    private final IPrendaRepository iPrendaRepository;
    private final PrendaMapper prendaMapper;

    public PrendaServicio(
            IPrendaRepository iPrendaRepository,
            PrendaMapper prendaMapper) {
        this.iPrendaRepository = iPrendaRepository;
        this.prendaMapper = prendaMapper;
    }

    // Obtener todas las prendas
    public List<PrendaRequestDTO> getAll() {
        return prendaMapper.toDtoList(
                iPrendaRepository.findAll()
        );
    }

    // Obtener una prenda por ID
    public PrendaRequestDTO getById(Integer id) {
        Prenda prenda = iPrendaRepository.findById(id)
                .orElseThrow(() -> new PrendaNoEncontrada(
                        "No existe una prenda con ID: " + id
                ));

        return prendaMapper.toDTO(prenda);
    }

    // Crear una prenda
    @Transactional
    public PrendaRequestDTO create(PrendaRequestDTO dto) {
        Prenda prenda = prendaMapper.toEntity(dto);
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

        prenda.setEstado(dto.getEstado());
        prenda.setNombre(dto.getNombre());
        prenda.setTalla(dto.getTalla());
        prenda.setMarca(dto.getMarca());
        prenda.setTipo(dto.getTipo());
        prenda.setFechaPublicacion(dto.getFechaPublicacion());

        Prenda actualizada = iPrendaRepository.save(prenda);

        return prendaMapper.toDTO(actualizada);
    }

    // Eliminar una prenda
    @Transactional
    public void delete(Integer id) {
        if (!iPrendaRepository.existsById(id)) {
            throw new PrendaNoEncontrada(
                    "No existe una prenda con ID: " + id
            );
        }

        iPrendaRepository.deleteById(id);
    }
}
