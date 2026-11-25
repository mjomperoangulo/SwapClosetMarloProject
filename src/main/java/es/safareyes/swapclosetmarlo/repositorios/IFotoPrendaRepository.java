package es.safareyes.swapclosetmarlo.repositorios;

import es.safareyes.swapclosetmarlo.modelos.FotoPrenda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFotoPrendaRepository extends JpaRepository<FotoPrenda , Integer> {

    Integer countByPrendaId(Integer prendaId);


}
