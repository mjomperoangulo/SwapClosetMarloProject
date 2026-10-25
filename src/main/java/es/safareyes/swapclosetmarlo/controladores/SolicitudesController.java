
package es.safareyes.swapclosetmarlo.controladores;

import es.safareyes.swapclosetmarlo.dtos.SolicitudesRequestDTO;
import es.safareyes.swapclosetmarlo.servicios.SolicitudesServicio;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/solicitudes")
public class SolicitudesController {

    private final SolicitudesServicio solicitudesServicio;

    public SolicitudesController(SolicitudesServicio solicitudesServicio) {
        this.solicitudesServicio = solicitudesServicio;
    }

    @GetMapping
    public ResponseEntity<List<SolicitudesRequestDTO>> getAll() {
        return ResponseEntity.ok(solicitudesServicio.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SolicitudesRequestDTO> getById(
            @PathVariable Integer id) {
        return ResponseEntity.ok(solicitudesServicio.getById(id));
    }

    @PostMapping
    public ResponseEntity<SolicitudesRequestDTO> create(
            @RequestBody SolicitudesRequestDTO dto) {
        return ResponseEntity.ok(solicitudesServicio.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SolicitudesRequestDTO> update(
            @PathVariable Integer id,
            @RequestBody SolicitudesRequestDTO dto) {
        return ResponseEntity.ok(solicitudesServicio.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        solicitudesServicio.delete(id);
        return ResponseEntity.noContent().build();
    }
}
