package org.sid.services;

import java.util.stream.Stream;

import org.sid.entities.Salle;
import org.sid.entities.Ville;
import org.sid.entities.Categorie;
import org.sid.entities.Centre;
import org.sid.entities.Place;
import org.sid.interfaces.ITest;
import org.sid.repositories.CategorieRepository;
import org.sid.repositories.CentreRepository;
import org.sid.repositories.PlaceRepository;
import org.sid.repositories.SalleRepository;
import org.sid.repositories.VilleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ITestService implements ITest {


	@Autowired
	public VilleRepository villeRepository;
	
	@Autowired
	public CentreRepository centreRepository;

	@Autowired
	public SalleRepository salleRepository;

	@Autowired
	public PlaceRepository placeRepository;

	@Autowired
	public CategorieRepository categorieRepository;

	
	@Override
	public void initVille() {
		Stream.of("Casablanca", "Rabat", "Marrakech", "Tanger").forEach(nameVille -> {
			Ville ville = new Ville();
			ville.setName(nameVille);
			ville.setNbrCentre(1 + (int) (Math.random() * 2));
			villeRepository.save(ville);
		});
	}

	@Override
	public void initCentre() {
		villeRepository.findAll().forEach(ville -> {
			for (int i = 0; i < ville.getNbrCentre(); i++){
				Centre centre = new Centre();
				centre.setName(ville.getName() + " - Centre" + (i + 1));
				centre.setNbrSalle(2 + (int) (Math.random() * 3));
				centre.setVille(ville);
				centreRepository.save(centre);
			}
		});
	}

	@Override
	public void initSalle() {
		centreRepository.findAll().forEach(centre -> {
			for (int i = 0; i < centre.getNbrSalle(); i++) {
				Salle salle = new Salle();
				salle.setCentre(centre);
				salle.setName( centre.getName() + " - Salle" + (i + 1));
				salle.setNbrPlace(10 + (int) (Math.random() * 15));
				salleRepository.save(salle);
			}
		});

	}

	@Override
	public void initPlace() {

		salleRepository.findAll().forEach(salle -> {
			for (int i = 0; i < salle.getNbrPlace(); i++) {
				Place place = new Place();
				place.setNumero(i + 1);
				place.setSalle(salle);
				placeRepository.save(place);
			}
		});

	}

	@Override
	public void initCategorie() {
		Stream.of("Algorithme", "Big Data", "Data Analysis", "Artificial Intelligence", "Web Development",
				"Cyber Security", "Data Science", "DevOps", "Cloud Computing", "Machine learning")
				.forEach(nameCategorie -> {
					Categorie categorie = new Categorie();
					categorie.setName(nameCategorie);
					categorieRepository.save(categorie);
				});
	}

}
