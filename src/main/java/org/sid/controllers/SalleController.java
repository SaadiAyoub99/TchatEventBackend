package org.sid.controllers;

import java.util.List;

import org.sid.entities.Salle;
import org.sid.services.SalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class SalleController {

	
	@Autowired
	public SalleService salleService;
	
	
	@GetMapping("/AllSalle")
	public List<Salle> findAllSalle(){
		return salleService.findAllSalleByName();
	}
}
