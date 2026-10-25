package es.safareyes.swapclosetmarlo.mappers;


import es.safareyes.swapclosetmarlo.dtos.EstilosRequestDTO;
import es.safareyes.swapclosetmarlo.modelos.Estilos;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EstilosMapper {
    EstilosRequestDTO toDto(Estilos estilos);
    Estilos toEntity(EstilosRequestDTO dto);



}
