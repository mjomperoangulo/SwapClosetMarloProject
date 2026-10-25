package es.safareyes.swapclosetmarlo.mappers;

import es.safareyes.swapclosetmarlo.dtos.ValoracionesRequestDTO;
import es.safareyes.swapclosetmarlo.modelos.Valoraciones;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ValoracionesMapper {

    ValoracionesRequestDTO toDTO(Valoraciones valoraciones);

    Valoraciones toEntity(ValoracionesRequestDTO dto);

    List<ValoracionesRequestDTO> toDTOList(List<Valoraciones> valoraciones);
}