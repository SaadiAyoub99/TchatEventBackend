package org.sid.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;
import org.sid.enums.Etat;
import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DemandeEvent implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titre;
	private String description;
	private int prixPlace;
	private String heureDebut;
	private Date dateOrganisation;
	
	@Column(length = 32, columnDefinition = "varchar(32) default 'PENDING'")
	@Enumerated(value = EnumType.STRING)
	private Etat status = Etat.PENDING;
	
	
	@OneToOne
	@JoinColumn(name = "salle_id", referencedColumnName = "id")
	private Salle salle;
	
	@ManyToOne
	@JoinColumn(name = "idUser", nullable=false)
	private IUser user;
	
	@ManyToOne
	private Categorie categorie;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "Events_Image",
	joinColumns = {
			@JoinColumn(name = "EVENT_ID")
	},
	inverseJoinColumns = {
			@JoinColumn(name = "IMAGE_ID")
	}
)
	private Set<ImageModel> EventImage;
}
