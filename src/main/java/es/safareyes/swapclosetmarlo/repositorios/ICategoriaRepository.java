package es.safareyes.swapclosetmarlo.repositorios;

import es.safareyes.swapclosetmarlo.modelos.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoriaRepository extends JpaRepository<Categoria, Integer> {
}
