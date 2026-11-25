package es.safareyes.swapclosetmarlo.servicios;

import es.safareyes.swapclosetmarlo.dtos.FotoPrendaResponseDTO;
import es.safareyes.swapclosetmarlo.modelos.Estado;
import es.safareyes.swapclosetmarlo.modelos.FotoPrenda;
import es.safareyes.swapclosetmarlo.modelos.Prenda;
import es.safareyes.swapclosetmarlo.repositorios.IFotoPrendaRepository;
import es.safareyes.swapclosetmarlo.repositorios.IPrendaRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FotoPrendaServicio {
    private final IFotoPrendaRepository fotoPrendaRepository;
    private final IPrendaRepository prendaRepository;

    public FotoPrendaServicio(IFotoPrendaRepository fotoPrendaRepository, IPrendaRepository prendaRepository) {
        this.fotoPrendaRepository = fotoPrendaRepository;
        this.prendaRepository = prendaRepository;
    }

    public void guardarFotoPrenda(Integer prenda_Id, String url) {

        long cantidad = fotoPrendaRepository.countByPrenda_Id(prenda_Id);

        if(cantidad >= 5){
            throw new RuntimeException("Maximo 5 fotos por prenda");
        }

        Prenda prenda = prendaRepository.findById(prenda_Id).orElseThrow(() -> new RuntimeException("La prenda que intentas modificar no existe"));

        FotoPrenda foto = new FotoPrenda();

        foto.setUrl(url);
        foto.setPrenda(prenda);

        fotoPrendaRepository.save(foto);

        if(cantidad == 0){
            prenda.setEstado(Estado.disponible);
            prendaRepository.save(prenda);
        }



    }



}
