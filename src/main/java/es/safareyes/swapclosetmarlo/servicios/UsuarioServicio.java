
package es.safareyes.swapclosetmarlo.servicios;

import es.safareyes.swapclosetmarlo.dtos.RegistroUsuarioDTO;
import es.safareyes.swapclosetmarlo.dtos.UsuarioDTO;
import es.safareyes.swapclosetmarlo.errores.UsuarioNoEncontrado;
import es.safareyes.swapclosetmarlo.mappers.UsuarioMapper;
import es.safareyes.swapclosetmarlo.modelos.Usuario;
import es.safareyes.swapclosetmarlo.repositorios.IUsuarioRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UsuarioServicio {

    private final IUsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioServicio(
            IUsuarioRepository usuarioRepository,
            UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    // Obtener todos los usuarios
    public List<UsuarioDTO> getAll() {
        return usuarioMapper.toDTOList(
                usuarioRepository.findAll()
        );
    }

    // Obtener un usuario por ID
    public UsuarioDTO getById(Integer id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNoEncontrado(
                        "No existe un usuario con ID: " + id
                ));

        return usuarioMapper.toDTO(usuario);
    }

    // Crear un usuario
    @Transactional
    public UsuarioDTO create(RegistroUsuarioDTO dto) {
        Usuario usuario = usuarioMapper.toEntity(dto);

        Usuario guardado = usuarioRepository.save(usuario);

        return usuarioMapper.toDTO(guardado);
    }

    // Actualizar un usuario
    @Transactional
    public UsuarioDTO update(Integer id, UsuarioDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNoEncontrado(
                        "No existe un usuario con ID: " + id
                ));

        usuario.setNombre(dto.getNombre());
        usuario.setRol(dto.getRol());

        Usuario actualizado = usuarioRepository.save(usuario);

        return usuarioMapper.toDTO(actualizado);
    }

    // Eliminar un usuario
    @Transactional
    public void delete(Integer id) {
        if (!usuarioRepository.existsById(id)) {
            throw new UsuarioNoEncontrado(
                    "No existe un usuario con ID: " + id
            );
        }

        usuarioRepository.deleteById(id);
    }
}
