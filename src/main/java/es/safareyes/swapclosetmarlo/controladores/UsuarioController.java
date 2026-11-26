
package es.safareyes.swapclosetmarlo.controladores;

import es.safareyes.swapclosetmarlo.dtos.RegistroUsuarioDTO;
import es.safareyes.swapclosetmarlo.dtos.UsuarioDTO;
import es.safareyes.swapclosetmarlo.servicios.UsuarioServicio;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioServicio usuarioServicio;

    public UsuarioController(UsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> getAll() {
        return ResponseEntity.ok(usuarioServicio.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> getById(
            @PathVariable Integer id) {
        return ResponseEntity.ok(usuarioServicio.getById(id));
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> create(
            @RequestBody RegistroUsuarioDTO dto) {
        return ResponseEntity.ok(usuarioServicio.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> update(
            @PathVariable Integer id,
            @RequestBody UsuarioDTO dto) {
        return ResponseEntity.ok(usuarioServicio.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        usuarioServicio.delete(id);
        return ResponseEntity.noContent().build();
    }
}
