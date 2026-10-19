package es.safareyes.swapclosetmarlo.mappers;


import es.safareyes.swapclosetmarlo.dtos.ValoracionesDTO;
import es.safareyes.swapclosetmarlo.modelos.Valoraciones;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ValoracionesMapper {

        ValoracionesDTO toDTO(Valoraciones valoraciones);
        Valoraciones toEntity(ValoracionesDTO dto);

}
