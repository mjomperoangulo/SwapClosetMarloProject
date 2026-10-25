
package es.safareyes.swapclosetmarlo.controladores;

import es.safareyes.swapclosetmarlo.dtos.MensajesRequestDTO;
import es.safareyes.swapclosetmarlo.servicios.MensajesServicio;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mensajes")
public class MensajesController {

    private final MensajesServicio mensajesServicio;

    public MensajesController(MensajesServicio mensajesServicio) {
        this.mensajesServicio = mensajesServicio;
    }

    // Obtener todos los mensajes
    @GetMapping
    public ResponseEntity<List<MensajesRequestDTO>> getAll() {
        return ResponseEntity.ok(mensajesServicio.getAll());
    }

    // Obtener un mensaje por ID
    @GetMapping("/{id}")
    public ResponseEntity<MensajesRequestDTO> getById(
            @PathVariable Integer id) {
        return ResponseEntity.ok(mensajesServicio.getById(id));
    }

    // Crear un mensaje
    @PostMapping
    public ResponseEntity<MensajesRequestDTO> create(
            @RequestBody MensajesRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mensajesServicio.create(dto));
    }

    // Actualizar un mensaje
    @PutMapping("/{id}")
    public ResponseEntity<MensajesRequestDTO> update(
            @PathVariable Integer id,
            @RequestBody MensajesRequestDTO dto) {
        return ResponseEntity.ok(
                mensajesServicio.update(id, dto)
        );
    }

    // Eliminar un mensaje
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        mensajesServicio.delete(id);
        return ResponseEntity.noContent().build();
    }
}
