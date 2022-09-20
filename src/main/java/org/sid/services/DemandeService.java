package org.sid.services;

import java.util.List;

import javax.transaction.Transactional;

import org.sid.entities.DemandeEvent;
import org.sid.entities.IUser;
import org.sid.enums.Etat;
import org.sid.repositories.DemandeEventRepository;
import org.sid.repositories.UserRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class DemandeService{

	public final DemandeEventRepository demandeEventRepository;
	public final UserRepository userRepository;
	
	public DemandeEvent findById(Long id) {
		return demandeEventRepository.findById(id).get();
	}

	public List<DemandeEvent> findAll() {
		return demandeEventRepository.findAll();
	}
	
	public DemandeEvent addEvent(DemandeEvent demandeEvent, Long id) {
		IUser userToFind = userRepository.findById(id).get();
		demandeEvent.setUser(userToFind);
		return demandeEventRepository.save(demandeEvent);
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
	/*
	public List<DemandeEvent> getAllAcceptedDemandes(){
		return demandeEventRepository.getAllAcceptedDemandes();
	}
	
	public List<DemandeEvent> getAcceptedEventByUserId(Long id ){
		return demandeEventRepository.getAcceptedEventByUserId(id);
	}
	
	public List<DemandeEvent> getRefusedEventByUserId(Long id ){
		return demandeEventRepository.getRefusedEventByUserId(id);
	}
	
	public List<DemandeEvent> getPendingEventByUserId(Long id ){
		return demandeEventRepository.getPendingEventByUserId(id);
	}
	*/
	
    public void deleteEvent(Long id) {
    	demandeEventRepository.deleteById(id);
    }
    



}