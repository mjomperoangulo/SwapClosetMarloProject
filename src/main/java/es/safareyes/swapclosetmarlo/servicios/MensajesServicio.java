package es.safareyes.swapclosetmarlo.servicios;

import es.safareyes.swapclosetmarlo.dtos.CategoriaDTO;
import es.safareyes.swapclosetmarlo.dtos.MensajesDTO;
import es.safareyes.swapclosetmarlo.mappers.CategoriaMapper;
import es.safareyes.swapclosetmarlo.mappers.MensajesMapper;
import es.safareyes.swapclosetmarlo.repositorios.ICategoriaRepository;
import es.safareyes.swapclosetmarlo.repositorios.IMensajesRepository;

import java.util.List;

public class MensajesServicio {
//    private final ICategoriaRepository iCategoriaRepository;
//    private final CategoriaMapper categoriaMapper;
//
//
//    public CategoriaServicio(ICategoriaRepository iCategoriaRepository, CategoriaMapper categoriaMapper) {
//        this.iCategoriaRepository = iCategoriaRepository;
//        this.categoriaMapper = categoriaMapper;
//    }
//
//
//    public List<CategoriaDTO> getAll() {
//        return categoriaMapper.toDtoList(iCategoriaRepository.findAll());
//    }

    private final IMensajesRepository iMensajesRepository;
    private final MensajesMapper mensajesMapper;

    public MensajesServicio(IMensajesRepository iMensajesRepository, MensajesMapper mensajesMapper) {
        this.iMensajesRepository = iMensajesRepository;
        this.mensajesMapper = mensajesMapper;
    }

    public List<MensajesDTO> getAll() {
        return  mensajesMapper.toDtoList(iMensajesRepository.findAll());
    }





}


