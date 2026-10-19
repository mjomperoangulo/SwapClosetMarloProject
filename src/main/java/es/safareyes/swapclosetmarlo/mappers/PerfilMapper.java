package es.safareyes.swapclosetmarlo.mappers;
import org.mapstruct.Mapper;

import es.safareyes.swapclosetmarlo.dtos.PerfilDTO;
import es.safareyes.swapclosetmarlo.modelos.Perfil;

import java.util.List;


@Mapper(componentModel = "spring")
public interface PerfilMapper {
    PerfilDTO toDto(Perfil perfil);
    Perfil toEntity(PerfilDTO dto);


    List<PerfilDTO> toDtoList(List<Perfil> all);
}

