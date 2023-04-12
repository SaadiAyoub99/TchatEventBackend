package org.sid.services;

import java.util.List;

import javax.transaction.Transactional;

import org.sid.entities.DemandeEvent;
import org.sid.entities.IUser;
import org.sid.entities.Place;
import org.sid.repositories.DemandeEventRepository;
import org.sid.repositories.PlaceRepository;
import org.sid.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
@Transactional
public class PlaceService {
	
	public final DemandeEventRepository demandeEventRepository;
	public final UserRepository userRepository;
	
	@Autowired
	public PlaceRepository placeRepository;
	
	public List<Place> findPlaceBySalleId(Long id){
		return placeRepository.findPlaceBySalleId(id);
	}
		
	public Place findPlaceById(Long id) {
		return placeRepository.findById(id).get();
	}
	
	public List<Place> findAllPlaces(){
		return placeRepository.findAll();
	}
	
	public void reservedPlace(Long idPlace, Long idUser) {
		Place place = findPlaceById(idPlace);
		IUser user = userRepository.findById(idUser).get();
		place.setUser(user);
		place.setReserved(true);
		placeRepository.save(place);
	}
	
	public void CancelReservedPlace(Long id) {
		Place place = findPlaceById(id);
		place.setReserved(false);
		placeRepository.save(place);
	}
	
	public List<Place> getAllReserverPlaces(){
		return placeRepository.getAllReservedPlaces();
	}
	
	

}
