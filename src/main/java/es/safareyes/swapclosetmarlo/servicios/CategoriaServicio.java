package es.safareyes.swapclosetmarlo.servicios;

import es.safareyes.swapclosetmarlo.dtos.CategoriaDTO;
import es.safareyes.swapclosetmarlo.dtos.PerfilDTO;
import es.safareyes.swapclosetmarlo.mappers.CategoriaMapper;
import es.safareyes.swapclosetmarlo.mappers.PerfilMapper;
import es.safareyes.swapclosetmarlo.repositorios.ICategoriaRepository;
import es.safareyes.swapclosetmarlo.repositorios.IPerfilRepository;

import java.util.List;

public class CategoriaServicio {

    private final ICategoriaRepository iCategoriaRepository;
    private final CategoriaMapper categoriaMapper;


    public CategoriaServicio(ICategoriaRepository iCategoriaRepository, CategoriaMapper categoriaMapper) {
        this.iCategoriaRepository = iCategoriaRepository;
        this.categoriaMapper = categoriaMapper;
    }


    public List<CategoriaDTO> getAll() {
        return categoriaMapper.toDtoList(iCategoriaRepository.findAll());
    }







}
