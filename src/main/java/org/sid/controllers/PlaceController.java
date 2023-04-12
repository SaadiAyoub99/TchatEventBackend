package org.sid.controllers;

import java.util.List;

import org.sid.entities.DemandeEvent;
import org.sid.entities.Place;
import org.sid.services.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class PlaceController {
	
	@Autowired
	public PlaceService placeService;
	
	
	@GetMapping("/PlaceBySalle")
	public List<Place> findPlaceBySalleId(@RequestParam Long id){
		return placeService.findPlaceBySalleId(id);
	}
	
	@GetMapping("/AllPlace")
	public List<Place> findPlaceBySalleId(){
		return placeService.findAllPlaces();
	}
	
//	@GetMapping("/Place")
//	@PreAuthorize("hasRole('User')")
//	public Place findPlaceById(@RequestParam Long id) {
//		return placeService.findPlaceById(id);
//	}
	
	
	@PostMapping("/reserved/{idPlace}")
	public void placeReserverd(@PathVariable Long idPlace, @RequestParam Long idUser) {
		placeService.reservedPlace(idPlace, idUser);
	}
	
	@PostMapping("/annulerReservation")
	@PreAuthorize("hasRole('Admin')")
	public void CancelReservedPlace( @RequestParam Long id) {
		placeService.CancelReservedPlace(id);
	}
	
	@GetMapping("/AllReserved")
	@PreAuthorize("hasRole('Admin')")
	public List<Place> getAllReserverPlaces(){
		return placeService.getAllReserverPlaces();
	}
	

}
