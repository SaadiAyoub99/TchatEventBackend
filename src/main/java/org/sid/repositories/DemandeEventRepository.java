package org.sid.repositories;

import java.util.List;

import org.sid.entities.DemandeEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin("*")
public interface DemandeEventRepository extends JpaRepository<DemandeEvent, Long>{
	
	@Query("select d from DemandeEvent d where d.status like 'PENDING'")
	public List<DemandeEvent> getAllPendingDemandes();
	/*
	@Query("select d from DemandeEvent d where d.status like 'ACCEPTED'")
	public List<DemandeEvent> getAllAcceptedDemandes();
	
	@Query("select d from demande_event d where d.iuser.id = :id_user and d.status like 'ACCEPTED' ")
	public List<DemandeEvent> getAcceptedEventByUserId(Long id );

	@Query("select d from demande_event d where d.iuser.id = :id_user and d.status like 'REFUSED' ")
	public List<DemandeEvent> getRefusedEventByUserId(Long id );
	
	@Query("select d from demande_event d where d.iuser.id = :id_user and d.status like 'PENDING' ")
	public List<DemandeEvent> getPendingEventByUserId(Long id );
	
	
	@Modifying
	@Query("delete from demande_event d where d.id=:id")
	void deleteAcceptedDemande(@Param("id") Long id);
	*/
}
