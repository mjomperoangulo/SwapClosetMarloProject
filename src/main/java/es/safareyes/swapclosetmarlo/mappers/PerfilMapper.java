package es.safareyes.swapclosetmarlo.mappers;
import es.safareyes.swapclosetmarlo.dtos.PerfilRequestDTO;
import org.mapstruct.Mapper;
import es.safareyes.swapclosetmarlo.modelos.Perfil;

import java.util.List;


@Mapper(componentModel = "spring")
public interface PerfilMapper {
    PerfilRequestDTO toDto(Perfil perfil);
    Perfil toEntity(PerfilRequestDTO dto);


    List<PerfilRequestDTO> toDtoList(List<Perfil> all);
}

