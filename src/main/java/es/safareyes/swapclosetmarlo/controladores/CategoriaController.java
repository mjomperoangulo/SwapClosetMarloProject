
package es.safareyes.swapclosetmarlo.controladores;

import es.safareyes.swapclosetmarlo.dtos.CategoriaRequestDTO;
import es.safareyes.swapclosetmarlo.servicios.CategoriaServicio;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    private final CategoriaServicio categoriaServicio;

    public CategoriaController(CategoriaServicio categoriaServicio) {
        this.categoriaServicio = categoriaServicio;
    }

    // Obtener todas las categorías
    @GetMapping
    public ResponseEntity<List<CategoriaRequestDTO>> getAll() {
        return ResponseEntity.ok(categoriaServicio.getAll());
    }

    // Obtener una categoría por ID
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaRequestDTO> getById(
            @PathVariable Integer id) {
        return ResponseEntity.ok(categoriaServicio.getById(id));
    }

    // Crear una categoría
    @PostMapping
    public ResponseEntity<CategoriaRequestDTO> create(
            @RequestBody CategoriaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoriaServicio.create(dto));
    }

    // Actualizar una categoría
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaRequestDTO> update(
            @PathVariable Integer id,
            @RequestBody CategoriaRequestDTO dto) {
        return ResponseEntity.ok(
                categoriaServicio.update(id, dto)
        );
    }

    // Eliminar una categoría
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        categoriaServicio.delete(id);
        return ResponseEntity.noContent().build();
    }
}
