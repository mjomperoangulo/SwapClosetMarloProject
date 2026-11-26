package es.safareyes.swapclosetmarlo.controladores;

import es.safareyes.swapclosetmarlo.dtos.FotoPrendaResponseDTO;
import es.safareyes.swapclosetmarlo.modelos.FotoPrenda;
import es.safareyes.swapclosetmarlo.servicios.FotoPrendaServicio;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fotos")
public class FotoPrendaController {

    private final FotoPrendaServicio fotoPrendaServicio;

    public FotoPrendaController(FotoPrendaServicio fotoPrendaServicio) {
        this.fotoPrendaServicio = fotoPrendaServicio;
    }

    @PostMapping
    public void guardarFoto(@RequestParam Integer idPrenda,
                            @RequestBody FotoPrenda foto) {
        fotoPrendaServicio.guardarFotoPrenda(idPrenda, foto.getUrl());
    }




}