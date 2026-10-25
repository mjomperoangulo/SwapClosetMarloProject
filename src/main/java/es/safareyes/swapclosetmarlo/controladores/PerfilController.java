
package es.safareyes.swapclosetmarlo.controladores;

import es.safareyes.swapclosetmarlo.dtos.PerfilRequestDTO;
import es.safareyes.swapclosetmarlo.servicios.PerfilServicio;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/perfiles")
public class PerfilController {

    private final PerfilServicio perfilServicio;

    public PerfilController(PerfilServicio perfilServicio) {
        this.perfilServicio = perfilServicio;
    }

    // GET: obtener todos los perfiles
    @GetMapping
    public List<PerfilRequestDTO> getAll() {
        return perfilServicio.getAll();
    }

    // GET: obtener un perfil por ID
    @GetMapping("/{id}")
    public PerfilRequestDTO getById(@PathVariable Integer id) {
        return perfilServicio.getById(id);
    }

    // POST: crear un perfil
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PerfilRequestDTO create(
            @RequestBody PerfilRequestDTO dto) {
        return perfilServicio.create(dto);
    }

    // PUT: actualizar un perfil
    @PutMapping("/{id}")
    public PerfilRequestDTO update(
            @PathVariable Integer id,
            @RequestBody PerfilRequestDTO dto) {
        return perfilServicio.update(id, dto);
    }

    // DELETE: eliminar un perfil
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        perfilServicio.delete(id);
    }
}
