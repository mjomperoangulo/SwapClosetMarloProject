
package es.safareyes.swapclosetmarlo.servicios;

import es.safareyes.swapclosetmarlo.dtos.MensajesRequestDTO;
import es.safareyes.swapclosetmarlo.errores.MensajesNoEncontrado;
import es.safareyes.swapclosetmarlo.mappers.MensajesMapper;
import es.safareyes.swapclosetmarlo.modelos.Mensajes;
import es.safareyes.swapclosetmarlo.repositorios.IMensajesRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MensajesServicio {

    private final IMensajesRepository iMensajesRepository;
    private final MensajesMapper mensajesMapper;

    public MensajesServicio(
            IMensajesRepository iMensajesRepository,
            MensajesMapper mensajesMapper) {
        this.iMensajesRepository = iMensajesRepository;
        this.mensajesMapper = mensajesMapper;
    }

    // Obtener todos los mensajes
    public List<MensajesRequestDTO> getAll() {
        return mensajesMapper.toDtoList(
                iMensajesRepository.findAll()
        );
    }

    // Obtener un mensaje por ID
    public MensajesRequestDTO getById(Integer id) {
        Mensajes mensaje = iMensajesRepository.findById(id)
                .orElseThrow(() -> new MensajesNoEncontrado(
                        "No existe un mensaje con ID: " + id
                ));

        return mensajesMapper.toDTO(mensaje);
    }

    // Crear un mensaje
    @Transactional
    public MensajesRequestDTO create(MensajesRequestDTO dto) {
        Mensajes mensaje = mensajesMapper.toEntity(dto);
        Mensajes guardado = iMensajesRepository.save(mensaje);

        return mensajesMapper.toDTO(guardado);
    }

    // Actualizar un mensaje
    @Transactional
    public MensajesRequestDTO update(
            Integer id, MensajesRequestDTO dto) {

        Mensajes mensaje = iMensajesRepository.findById(id)
                .orElseThrow(() -> new MensajesNoEncontrado(
                        "No existe un mensaje con ID: " + id
                ));

        mensaje.setContenido(dto.getContenido());

        Mensajes actualizado = iMensajesRepository.save(mensaje);

        return mensajesMapper.toDTO(actualizado);
    }

    // Eliminar un mensaje
    @Transactional
    public void delete(Integer id) {
        if (!iMensajesRepository.existsById(id)) {
            throw new MensajesNoEncontrado(
                    "No existe un mensaje con ID: " + id
            );
        }

        iMensajesRepository.deleteById(id);
    }
}
