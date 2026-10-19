package es.safareyes.swapclosetmarlo.mappers;


import es.safareyes.swapclosetmarlo.dtos.MensajesDTO;
import es.safareyes.swapclosetmarlo.modelos.Mensajes;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MensajesMapper {

    MensajesDTO toDTO(Mensajes mensajes);
    Mensajes toEntity(MensajesDTO dto);

    List<MensajesDTO> toDtoList(List<Mensajes> all);
}
