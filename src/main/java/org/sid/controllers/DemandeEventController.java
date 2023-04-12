package org.sid.controllers;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.sid.entities.Categorie;
import org.sid.entities.DemandeEvent;
import org.sid.entities.IUser;
import org.sid.entities.ImageModel;
import org.sid.entities.Salle;
import org.sid.repositories.DemandeEventRepository;
import org.sid.repositories.UserRepository;
import org.sid.services.CategorieService;
import org.sid.services.DemandeService;
import org.sid.services.SalleService;
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
	public DemandeEventRepository demandeEventRepository;
	
	@Autowired
	public SalleService salleService;
	
	@Autowired
	public CategorieService categorieService;
	
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
	
	
	@PreAuthorize("hasRole('User')")
	@PostMapping(value = {"/createDemande/{idSalle}/{idCategorie}"}, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE	})
	public DemandeEvent addDemande (@RequestPart("demandeEvent") DemandeEvent demandeEvent, @RequestPart("imageFile") MultipartFile[] file , @RequestParam Long id, @PathVariable Long idSalle, @PathVariable Long idCategorie ) {
		//return demandeService.addEvent(demandeEvent, id, idSalle, idCategorie);
		try {
			Set<ImageModel> images = uploadImage(file);
			demandeEvent.setEventImage(images);
			return demandeService.addEvent(demandeEvent, id, idSalle, idCategorie);
		}catch (Exception e) {
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
	public void deleteEleve(@RequestParam Long id) {
		demandeService.deleteEvent(id);
	}
	
	@GetMapping("/getDemandeDetailsById/{id}")
	public DemandeEvent getDemandeDetailsById(@PathVariable Long id) {
		return demandeService.getDemandeDetailsById(id);
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
	
	@GetMapping("/MyPending/{id}")
	@PreAuthorize("hasRole('User')")
	public List<DemandeEvent> getPendingDemandeByUserId(@PathVariable Long id){
		return demandeService.getPendingDemandeByUserId(id);
	}
	
	@GetMapping("/MyAccepted/{id}")
	@PreAuthorize("hasRole('User')")
	public List<DemandeEvent> getAcceptedDemandeByUserId(@PathVariable Long id ){
    	return demandeService.getAcceptedDemandeByUserId(id);
	}
	
	@GetMapping("/MyRefused/{id}")
	@PreAuthorize("hasRole('User')")
	public List<DemandeEvent> getRefusedEventByUserId(@PathVariable Long id ){
    	return demandeService.getRefusedEventByUserId(id);
	}
	
	

	
	@GetMapping("/PublishedEvents")
	public List<DemandeEvent> getAllAcceptedDemandes(){
		return demandeService.getAllAcceptedDemandes();
	}
	
}



























