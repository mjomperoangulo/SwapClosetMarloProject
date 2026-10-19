package es.safareyes.swapclosetmarlo.servicios;

import es.safareyes.swapclosetmarlo.dtos.PerfilDTO;
import es.safareyes.swapclosetmarlo.mappers.PerfilMapper;
import es.safareyes.swapclosetmarlo.repositorios.IPerfilRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerfilServicio {

        private final IPerfilRepository iPerfilRepository;
        private final PerfilMapper perfilMapper;

    public PerfilServicio(IPerfilRepository iPerfilRepository, PerfilMapper perfilMapper) {
        this.iPerfilRepository = iPerfilRepository;
        this.perfilMapper = perfilMapper;
    }

    public List<PerfilDTO> getAll() {
            return perfilMapper.toDtoList(iPerfilRepository.findAll());
    }


}
