package es.safareyes.swapclosetmarlo.servicios;

import es.safareyes.swapclosetmarlo.modelos.FotoPrenda;
import es.safareyes.swapclosetmarlo.repositorios.IFotoPrendaRepository;
import es.safareyes.swapclosetmarlo.repositorios.IPrendaRepository;

public class FotoPrendaServicio {

    private final IFotoPrendaRepository fotoPrendaRepository;
    private final IPrendaRepository prendaRepository;


    public FotoPrendaServicio(IFotoPrendaRepository fotoPrendaRepository, IPrendaRepository prendaRepository) {
        this.fotoPrendaRepository = fotoPrendaRepository;
        this.prendaRepository = prendaRepository;
    }
}
