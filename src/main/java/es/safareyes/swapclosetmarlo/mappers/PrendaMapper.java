
package es.safareyes.swapclosetmarlo.mappers;

import es.safareyes.swapclosetmarlo.dtos.PrendaRequestDTO;
import es.safareyes.swapclosetmarlo.modelos.Prenda;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PrendaMapper {

    PrendaRequestDTO toDTO(Prenda prenda);

    Prenda toEntity(PrendaRequestDTO dto);

    List<PrendaRequestDTO> toDtoList(List<Prenda> all);
}
