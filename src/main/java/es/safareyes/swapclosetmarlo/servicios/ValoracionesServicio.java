
package es.safareyes.swapclosetmarlo.servicios;

import es.safareyes.swapclosetmarlo.dtos.ValoracionesRequestDTO;
import es.safareyes.swapclosetmarlo.errores.ValoracionesNoEncontrada;
import es.safareyes.swapclosetmarlo.mappers.ValoracionesMapper;
import es.safareyes.swapclosetmarlo.modelos.Valoraciones;
import es.safareyes.swapclosetmarlo.repositorios.IValoracionesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ValoracionesServicio {

    private final IValoracionesRepository valoracionesRepository;
    private final ValoracionesMapper valoracionesMapper;

    public ValoracionesServicio(
            IValoracionesRepository valoracionesRepository,
            ValoracionesMapper valoracionesMapper) {
        this.valoracionesRepository = valoracionesRepository;
        this.valoracionesMapper = valoracionesMapper;
    }

    public List<ValoracionesRequestDTO> getAll() {
        return valoracionesMapper.toDTOList(
                valoracionesRepository.findAll()
        );
    }

    public ValoracionesRequestDTO getById(Integer id) {
        Valoraciones valoracion = valoracionesRepository.findById(id)
                .orElseThrow(() -> new ValoracionesNoEncontrada(
                        "No existe una valoración con ID: " + id
                ));

        return valoracionesMapper.toDTO(valoracion);
    }

    @Transactional
    public ValoracionesRequestDTO create(ValoracionesRequestDTO dto) {
        Valoraciones valoracion = valoracionesMapper.toEntity(dto);
        Valoraciones guardada = valoracionesRepository.save(valoracion);

        return valoracionesMapper.toDTO(guardada);
    }

    @Transactional
    public ValoracionesRequestDTO update(
            Integer id, ValoracionesRequestDTO dto) {

        Valoraciones valoracion = valoracionesRepository.findById(id)
                .orElseThrow(() -> new ValoracionesNoEncontrada(
                        "No existe una valoración con ID: " + id
                ));

        valoracion.setComentario(dto.getComentario());
        valoracion.setEstrellitas(dto.getEstrellitas());
        valoracion.setFecha(dto.getFecha());

        Valoraciones actualizada = valoracionesRepository.save(valoracion);

        return valoracionesMapper.toDTO(actualizada);
    }

    @Transactional
    public void delete(Integer id) {
        if (!valoracionesRepository.existsById(id)) {
            throw new ValoracionesNoEncontrada(
                    "No existe una valoración con ID: " + id
            );
        }

        valoracionesRepository.deleteById(id);
    }
}
