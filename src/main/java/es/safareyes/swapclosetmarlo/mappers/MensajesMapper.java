package es.safareyes.swapclosetmarlo.mappers;


import es.safareyes.swapclosetmarlo.dtos.MensajesRequestDTO;
import es.safareyes.swapclosetmarlo.modelos.Mensajes;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MensajesMapper {

    MensajesRequestDTO toDTO(Mensajes mensajes);
    Mensajes toEntity(MensajesRequestDTO dto);

    List<MensajesRequestDTO> toDtoList(List<Mensajes> all);
}
