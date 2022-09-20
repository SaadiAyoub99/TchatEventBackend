package org.sid.controllers;

import java.util.List;

import org.sid.entities.Categorie;
import org.sid.services.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategorieController {
	
	
	@Autowired
	public CategorieService categorieService;
	
	
	@GetMapping("/AllCategorie")
	@PreAuthorize("hasRole('User')")
	public List<Categorie> findAllCategorie(){
		return categorieService.findAllCategorie();
	}

}
