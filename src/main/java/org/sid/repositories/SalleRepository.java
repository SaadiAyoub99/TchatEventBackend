package org.sid.repositories;

import org.sid.entities.DemandeEvent;
import org.sid.entities.Salle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalleRepository  extends JpaRepository<Salle, Long>{

}
