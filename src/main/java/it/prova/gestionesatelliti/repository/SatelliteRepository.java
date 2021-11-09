package it.prova.gestionesatelliti.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.gestionesatelliti.model.Satellite;
import it.prova.gestionesatelliti.model.StatoSatellite;

public interface SatelliteRepository extends CrudRepository<Satellite, Long>{
	
	@Query("select s from Satellite s where s.dataLancio < ?1 and s.stato != 'DISATTIVATO'")
	List<Satellite> findAllByLanciatiDaPiuDiUnAnnoENonDisattivati(Date data);
	
	List<Satellite> findAllByStatoLikeAndDataRientroIsNull(StatoSatellite statoSatellite);
	
	List<Satellite> findAllByDataLancioLessThanAndStatoLike(Date date, StatoSatellite statoSatellite);
}
