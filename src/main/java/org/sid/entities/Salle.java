package org.sid.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Salle implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	
	@JsonIgnore
	private int nbrPlace;

	@JsonIgnore
	@ManyToOne
	private Centre centre;
	
	@JsonIgnore
	@OneToMany(mappedBy = "salle")
	private List<Place> places;
	
	@JsonIgnore
	@OneToOne(mappedBy = "salle")
	private DemandeEvent demandeEvent;
}
