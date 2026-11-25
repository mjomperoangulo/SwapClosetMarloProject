package es.safareyes.swapclosetmarlo.repositorios;

import es.safareyes.swapclosetmarlo.modelos.FotoPrenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface IFotoPrendaRepository extends JpaRepository<FotoPrenda, Integer> {

    long countByPrenda_Id(Integer prenda_Id);






}
