package es.safareyes.swapclosetmarlo.mappers;


import es.safareyes.swapclosetmarlo.dtos.FotoPrendaRequestDTO;
import es.safareyes.swapclosetmarlo.modelos.FotoPrenda;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FotoPrendaMapper {

    FotoPrendaRequestDTO toDTO(FotoPrenda fotoPrenda);
    FotoPrenda toEntity(FotoPrendaRequestDTO dto);

}
