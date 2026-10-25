
package es.safareyes.swapclosetmarlo.servicios;

import es.safareyes.swapclosetmarlo.dtos.PerfilRequestDTO;
import es.safareyes.swapclosetmarlo.mappers.PerfilMapper;
import es.safareyes.swapclosetmarlo.modelos.Perfil;
import es.safareyes.swapclosetmarlo.repositorios.IPerfilRepository;
import es.safareyes.swapclosetmarlo.errores.PerfilNoEncontrado;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PerfilServicio {

    private final IPerfilRepository iPerfilRepository;
    private final PerfilMapper perfilMapper;

    public PerfilServicio(
            IPerfilRepository iPerfilRepository,
            PerfilMapper perfilMapper) {
        this.iPerfilRepository = iPerfilRepository;
        this.perfilMapper = perfilMapper;
    }

    // Obtener todos los perfiles
    public List<PerfilRequestDTO> getAll() {
        return perfilMapper.toDtoList(
                iPerfilRepository.findAll()
        );

    }

    // Obtener un perfil por ID

    public PerfilRequestDTO getById(Integer id) {
        Perfil perfil = iPerfilRepository.findById(id)
                .orElseThrow(() -> new PerfilNoEncontrado(
                        "No existe un perfil con ID: " + id
                ));

        return perfilMapper.toDto(perfil);
    }


    // Crear un perfil
    @Transactional
    public PerfilRequestDTO create(PerfilRequestDTO dto) {
        Perfil perfil = perfilMapper.toEntity(dto);
        Perfil guardado = iPerfilRepository.save(perfil);

        return perfilMapper.toDto(guardado);
    }

    // Actualizar un perfil

    @Transactional
    public PerfilRequestDTO update(Integer id, PerfilRequestDTO dto) {

        Perfil perfil = iPerfilRepository.findById(id)
                .orElseThrow(() -> new PerfilNoEncontrado(
                        "No existe un perfil con ID: " + id
                ));

        perfil.setApellido1(dto.getApellido1());
        perfil.setApellido2(dto.getApellido2());
        perfil.setCiudad(dto.getCiudad());
        perfil.setNombre(dto.getNombre());
        perfil.setFechanacimiento(dto.getFechanacimiento());
        perfil.setTalla(dto.getTalla());
        perfil.setEmail(dto.getEmail());

        Perfil actualizado = iPerfilRepository.save(perfil);

        return perfilMapper.toDto(actualizado);
    }


    // Eliminar un perfil

    @Transactional
    public void delete(Integer id) {
        if (!iPerfilRepository.existsById(id)) {
            throw new PerfilNoEncontrado(
                    "No existe un perfil con ID: " + id
            );
        }

        iPerfilRepository.deleteById(id);
    }







}
