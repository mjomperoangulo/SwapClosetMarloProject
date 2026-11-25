
package es.safareyes.swapclosetmarlo.servicios;

import es.safareyes.swapclosetmarlo.dtos.CategoriaRequestDTO;
import es.safareyes.swapclosetmarlo.errores.CategoriaNoEncontrada;
import es.safareyes.swapclosetmarlo.mappers.CategoriaMapper;
import es.safareyes.swapclosetmarlo.modelos.Categoria;
import es.safareyes.swapclosetmarlo.repositorios.ICategoriaRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CategoriaServicio {

    private final ICategoriaRepository iCategoriaRepository;
    private final CategoriaMapper categoriaMapper;

    public CategoriaServicio(
            ICategoriaRepository iCategoriaRepository,
            CategoriaMapper categoriaMapper) {
        this.iCategoriaRepository = iCategoriaRepository;
        this.categoriaMapper = categoriaMapper;
    }

    // Obtener todas las categorías
    public List<CategoriaRequestDTO> getAll() {
        return categoriaMapper.toDtoList(
                iCategoriaRepository.findAll()
        );
    }

    // Obtener una categoría por ID
    public CategoriaRequestDTO getById(Integer id) {
        Categoria categoria = iCategoriaRepository.findById(id)
                .orElseThrow(() -> new CategoriaNoEncontrada(
                        "No existe una categoría con ID: " + id
                ));

        return categoriaMapper.toDto(categoria);
    }

    // Crear una categoría
    @Transactional
    public CategoriaRequestDTO create(CategoriaRequestDTO dto) {
        Categoria categoria = categoriaMapper.toEntity(dto);
        Categoria guardada = iCategoriaRepository.save(categoria);

        return categoriaMapper.toDto(guardada);
    }

    // Actualizar una categoría
    @Transactional
    public CategoriaRequestDTO update(
            Integer id, CategoriaRequestDTO dto) {

        Categoria categoria = iCategoriaRepository.findById(id)
                .orElseThrow(() -> new CategoriaNoEncontrada(
                        "No existe una categoría con ID: " + id
                ));

        categoria.setNombre(dto.getNombre());

        Categoria actualizada = iCategoriaRepository.save(categoria);

        return categoriaMapper.toDto(actualizada);
    }

    // Eliminar una categoría
    @Transactional
    public void delete(Integer id) {
        if (!iCategoriaRepository.existsById(id)) {
            throw new CategoriaNoEncontrada(
                    "No existe una categoría con ID: " + id
            );
        }

        iCategoriaRepository.deleteById(id);
    }
}
