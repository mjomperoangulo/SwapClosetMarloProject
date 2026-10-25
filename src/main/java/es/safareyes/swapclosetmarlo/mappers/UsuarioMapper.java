
package es.safareyes.swapclosetmarlo.mappers;

import es.safareyes.swapclosetmarlo.dtos.RegistroUsuarioDTO;
import es.safareyes.swapclosetmarlo.dtos.UsuarioDTO;
import es.safareyes.swapclosetmarlo.modelos.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioDTO toDTO(Usuario usuario);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "rol", ignore = true)
    @Mapping(target = "perfil", ignore = true)
    Usuario toEntity(RegistroUsuarioDTO dto);

    List<UsuarioDTO> toDTOList(List<Usuario> usuarios);
}
