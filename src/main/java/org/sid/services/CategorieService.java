package org.sid.services;

import java.util.List;

import org.sid.entities.Categorie;
import org.sid.repositories.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CategorieService {

	
	@Autowired
	public CategorieRepository categorieRepository;
	
	
	public List<Categorie> findAllCategorie(){
		return categorieRepository.findAll();
	}
}
