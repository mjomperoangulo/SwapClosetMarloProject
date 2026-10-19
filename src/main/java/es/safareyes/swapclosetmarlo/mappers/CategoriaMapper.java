package es.safareyes.swapclosetmarlo.mappers;


import es.safareyes.swapclosetmarlo.dtos.CategoriaDTO;
import es.safareyes.swapclosetmarlo.modelos.Categoria;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {
        CategoriaDTO toDto(Categoria categoria);
        Categoria toEntity(CategoriaDTO dto);

}
