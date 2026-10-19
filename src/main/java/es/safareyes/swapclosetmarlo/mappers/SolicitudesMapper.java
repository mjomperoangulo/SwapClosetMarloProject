package es.safareyes.swapclosetmarlo.mappers;


import es.safareyes.swapclosetmarlo.dtos.SolicitudesDTO;
import es.safareyes.swapclosetmarlo.modelos.Solicitudes;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SolicitudesMapper {
    SolicitudesDTO toDto(Solicitudes solicitudes);
    Solicitudes toEntity(SolicitudesDTO dto);


}







