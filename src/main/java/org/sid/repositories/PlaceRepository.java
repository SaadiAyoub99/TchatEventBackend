package org.sid.repositories;

import java.util.List;

import org.sid.entities.DemandeEvent;
import org.sid.entities.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin("*")
public interface PlaceRepository  extends JpaRepository<Place, Long>{

	@Query("select p from Place p where p.salle.id = :id")
	public List<Place> findPlaceBySalleId(@Param("id") Long id);
	

	@Query("select p from Place p where p.reserved ='true' ")
	public List<Place> getAllReservedPlaces();
}
