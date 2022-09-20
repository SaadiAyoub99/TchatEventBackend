package org.sid.services;

import java.util.List;

import org.sid.entities.Salle;
import org.sid.repositories.SalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SalleService {

	
	@Autowired
	public SalleRepository salleRepository;
	
	
	public List<Salle> findAllSalle(){
		return salleRepository.findAll();
	}
}
