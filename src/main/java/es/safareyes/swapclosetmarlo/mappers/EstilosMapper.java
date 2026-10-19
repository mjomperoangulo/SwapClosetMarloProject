package es.safareyes.swapclosetmarlo.mappers;


import es.safareyes.swapclosetmarlo.dtos.EstilosDTO;
import es.safareyes.swapclosetmarlo.modelos.Estilos;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EstilosMapper {
    EstilosDTO toDto(Estilos estilos);
    Estilos toEntity(EstilosDTO dto);



}
