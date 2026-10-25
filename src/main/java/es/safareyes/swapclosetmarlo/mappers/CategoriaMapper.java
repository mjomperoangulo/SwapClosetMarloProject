package es.safareyes.swapclosetmarlo.mappers;



import es.safareyes.swapclosetmarlo.dtos.CategoriaRequestDTO;
import es.safareyes.swapclosetmarlo.modelos.Categoria;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {
        CategoriaRequestDTO toDto(Categoria categoria);
        Categoria toEntity(CategoriaRequestDTO dto);

        List<CategoriaRequestDTO> toDtoList(List<Categoria> all);
}
