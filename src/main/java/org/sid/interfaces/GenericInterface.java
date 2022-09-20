package org.sid.interfaces;

import java.util.List;
import java.util.Optional;

import org.sid.entities.DemandeEvent;
import org.sid.repositories.DemandeEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public interface GenericInterface<T>{

	public T add(T objet);
	public T update(Long id, T objet);
	public void delete(T objet);
	public T findById(Long id);
	public List<T> findAll();
	
	
}
