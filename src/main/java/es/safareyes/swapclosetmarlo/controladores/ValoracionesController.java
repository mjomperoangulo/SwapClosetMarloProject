
package es.safareyes.swapclosetmarlo.controladores;

import es.safareyes.swapclosetmarlo.dtos.ValoracionesRequestDTO;
import es.safareyes.swapclosetmarlo.servicios.ValoracionesServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/valoraciones")
public class ValoracionesController {

    private final ValoracionesServicio valoracionesServicio;

    public ValoracionesController(
            ValoracionesServicio valoracionesServicio) {
        this.valoracionesServicio = valoracionesServicio;
    }

    @GetMapping
    public ResponseEntity<List<ValoracionesRequestDTO>> getAll() {
        return ResponseEntity.ok(valoracionesServicio.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ValoracionesRequestDTO> getById(
            @PathVariable Integer id) {
        return ResponseEntity.ok(valoracionesServicio.getById(id));
    }

    @PostMapping
    public ResponseEntity<ValoracionesRequestDTO> create(
            @RequestBody ValoracionesRequestDTO dto) {
        return ResponseEntity.ok(valoracionesServicio.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ValoracionesRequestDTO> update(
            @PathVariable Integer id,
            @RequestBody ValoracionesRequestDTO dto) {
        return ResponseEntity.ok(valoracionesServicio.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        valoracionesServicio.delete(id);
        return ResponseEntity.noContent().build();
    }
}
