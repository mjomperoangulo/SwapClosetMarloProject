package es.safareyes.swapclosetmarlo.mappers;


import es.safareyes.swapclosetmarlo.dtos.MensajesDTO;
import es.safareyes.swapclosetmarlo.modelos.Mensajes;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MensajesMapper {

    MensajesDTO toDTO(Mensajes mensajes);
    Mensajes toEntity(MensajesDTO dto);

}
