package org.sid.controllers;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.sid.entities.DemandeEvent;
import org.sid.entities.IUser;
import org.sid.entities.ImageModel;
import org.sid.repositories.UserRepository;
import org.sid.services.DemandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class DemandeEventController {

	@Autowired
	public DemandeService demandeService;
	
	@Autowired
	public UserRepository userRepository;
	
	
	@GetMapping("/ListDemandes")
	public List<DemandeEvent> getAllDemand() {
		return demandeService.findAll();
	}
	
	@GetMapping("/demande/{id}")
	public DemandeEvent getDemande(@PathVariable Long id) {
		return demandeService.findById(id);
	}
	
	
	@PostMapping(value = {"/createDemande"}, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	@PreAuthorize("hasRole('User')")
	public DemandeEvent addDemande (@RequestPart("demandeEvent") DemandeEvent demandeEvent, @RequestPart ("imageFile") MultipartFile[] file , @RequestParam Long id) {
		try {
			Set<ImageModel> images = uploadImage(file);
			demandeEvent.setEventImage(images);
			return demandeService.addEvent(demandeEvent, id);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		
		
	}
	
	public Set<ImageModel> uploadImage(MultipartFile[] multipartFiles) throws IOException{
		Set<ImageModel> imageModels = new HashSet<>();
		
		for (MultipartFile file: multipartFiles) {
			ImageModel imageModel = new ImageModel(
					file.getOriginalFilename(),
					file.getContentType(),
					file.getBytes()
					);
			imageModels.add(imageModel);
		}
		return imageModels;
	}
	
	
	@DeleteMapping("/SupprimerEvent")
	@PreAuthorize("hasRole('User')")
	public void deleteEleve(@RequestParam Long id) {
		demandeService.deleteEvent(id);
	}
	
	
	@PostMapping("/accepteDemande")
	@PreAuthorize("hasRole('Admin')")
	public void acceptedDemand( @RequestParam Long id) {
		demandeService.AcceptedDemande(id);
	}
	
	@PostMapping("/refuseDemande")
	@PreAuthorize("hasRole('Admin')")
	public void refuserDemand (@RequestParam Long id) {
		demandeService.RefusedDemande(id);
	}
	
	
	@GetMapping("/AllPending")
	@PreAuthorize("hasRole('Admin')")
	public List<DemandeEvent> getAllPendingDemandes(){
		return demandeService.getAllPendingDemandes();
	}
	/*
	@GetMapping("/PublishedEvents")
	public List<DemandeEvent> getAllAcceptedDemandes(){
		return demandeService.getAllAcceptedDemandes();
	}

	@GetMapping("/UserAccepted/{id}")
	@PreAuthorize("hasRole('User')")
	public List<DemandeEvent> getAcceptedEventByUserId(@PathVariable Long id ){
    	return demandeService.getAcceptedEventByUserId(id);
	}
	
	@GetMapping("/UserPending/{id}")
	@PreAuthorize("hasRole('User')")
	public List<DemandeEvent> getPendingEventByUserId(@PathVariable Long id ){
    	return demandeService.getPendingEventByUserId(id);
	}
	
	@GetMapping("/UserRefused/{id}")
	@PreAuthorize("hasRole('User')")
	public List<DemandeEvent> getRefusedEventByUserId(@PathVariable Long id ){
    	return demandeService.getRefusedEventByUserId(id);
	}
	*/
}



























