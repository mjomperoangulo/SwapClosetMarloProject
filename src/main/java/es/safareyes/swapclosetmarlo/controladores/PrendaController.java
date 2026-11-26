package es.safareyes.swapclosetmarlo.controladores;


import es.safareyes.swapclosetmarlo.dtos.PrendaRequestDTO;
import es.safareyes.swapclosetmarlo.servicios.PrendaServicio;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prendas")
public class PrendaController {
    private final PrendaServicio prendaServicio;

    public PrendaController(PrendaServicio prendaServicio) {
        this.prendaServicio = prendaServicio;
    }

    @GetMapping
    public List<PrendaRequestDTO> getAll() {
        return prendaServicio.getAll();
    }

    @PostMapping("/crear")
    public PrendaRequestDTO create(@RequestParam Integer idPerfil, @RequestParam Integer idCategoria, @RequestBody PrendaRequestDTO dto) {
        return prendaServicio.create(dto, idPerfil,idCategoria);


    }

}