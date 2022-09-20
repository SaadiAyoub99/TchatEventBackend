package org.sid.repositories;

import org.sid.entities.Centre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CentreRepository  extends JpaRepository<Centre, Long>{

}
