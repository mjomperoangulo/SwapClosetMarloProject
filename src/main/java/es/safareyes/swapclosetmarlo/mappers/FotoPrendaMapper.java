package es.safareyes.swapclosetmarlo.mappers;


import es.safareyes.swapclosetmarlo.dtos.FotoPrendaDTO;
import es.safareyes.swapclosetmarlo.modelos.FotoPrenda;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FotoPrendaMapper {

    FotoPrendaDTO toDTO(FotoPrenda fotoPrenda);
    FotoPrenda toEntity(FotoPrendaDTO dto);

}
