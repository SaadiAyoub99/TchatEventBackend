package org.sid.services;

import java.util.List;

import javax.transaction.Transactional;

import org.sid.entities.Categorie;
import org.sid.entities.DemandeEvent;
import org.sid.entities.IUser;
import org.sid.entities.Salle;
import org.sid.enums.Etat;
import org.sid.repositories.DemandeEventRepository;
import org.sid.repositories.PlaceRepository;
import org.sid.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class DemandeService{
	
	
	public final CategorieService categorieService;
	public final SalleService salleService;
	public final DemandeEventRepository demandeEventRepository;
	public final UserRepository userRepository;
	
	public DemandeEvent findById(Long id) {
		return demandeEventRepository.findById(id).get();
	}

	public List<DemandeEvent> findAll() {
		return demandeEventRepository.findAll();
	}
	
	public DemandeEvent addEvent(DemandeEvent demandeEvent, Long id, Long idSalle, Long idCategorie) {
		IUser userToFind = userRepository.findById(id).get();
		Salle salle = salleService.findSalleById(idSalle);
		Categorie categorie = categorieService.findCategorieById(idCategorie);
		demandeEvent.setSalle(salle);
		demandeEvent.setCategorie(categorie);
		demandeEvent.setUser(userToFind);
		return demandeEventRepository.save(demandeEvent);
	}
	
    public void deleteEvent(Long id) {
    	demandeEventRepository.deleteById(id);
    }
    
    public DemandeEvent getDemandeDetailsById(Long id) {
    	return demandeEventRepository.findById(id).get();
    }
    
	public void AcceptedDemande(Long id) {
		DemandeEvent demandeEvent = demandeEventRepository.findById(id).get();
		demandeEvent.setStatus(Etat.ACCEPTED);
		demandeEventRepository.save(demandeEvent);
	}
	
	public void RefusedDemande(Long id) {
		DemandeEvent demandeEvent = demandeEventRepository.findById(id).get();
		demandeEvent.setStatus(Etat.REFUSED);
		demandeEventRepository.save(demandeEvent);
	}
	
	
	public List<DemandeEvent> getAllPendingDemandes(){
		return demandeEventRepository.getAllPendingDemandes();
	}
	
	public List<DemandeEvent> getPendingDemandeByUserId(Long id){
		return demandeEventRepository.getPendingDemandeByUserId(id);
	}
	
	
	public List<DemandeEvent> getAcceptedDemandeByUserId(Long id ){
		return demandeEventRepository.getAcceptedDemandeByUserId(id);
	}
	
	
	public List<DemandeEvent> getRefusedEventByUserId(Long id ){
		return demandeEventRepository.getRefusedDemandeByUserId(id);
	}
	
	
	public List<DemandeEvent> getAllAcceptedDemandes(){
		return demandeEventRepository.getAllAcceptedDemandes();
	}
	
	
	




}