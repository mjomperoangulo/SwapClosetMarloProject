
package es.safareyes.swapclosetmarlo.mappers;

import es.safareyes.swapclosetmarlo.dtos.SolicitudesRequestDTO;
import es.safareyes.swapclosetmarlo.modelos.Solicitudes;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SolicitudesMapper {

    SolicitudesRequestDTO toDto(Solicitudes solicitudes);

    Solicitudes toEntity(SolicitudesRequestDTO dto);

    List<SolicitudesRequestDTO> toDtoList(List<Solicitudes> solicitudes);
}
