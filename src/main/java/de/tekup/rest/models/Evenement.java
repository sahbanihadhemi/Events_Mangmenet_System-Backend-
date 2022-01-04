package de.tekup.rest.models;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Evenement {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idEvents;
	
	private String nom;
	
	private String categorie;
	private String lien;
	private String lieu;
	private String descriptionLieu;
    private String about;
	private LocalDateTime dateDebut;
	private LocalDateTime dateFin;
private String dateDebutString;
private String dateFinString;
private LocalTime debut;
private LocalTime fin;
private String description;
private String linkSponsorsImg;
private String schedule;
@JsonIgnore

@OneToMany(mappedBy = "event")
private List<UserEvent> UserEvents = new ArrayList<>();
 

@JsonIgnore
@ManyToMany()
@JoinTable(name="Event_Sponsor",joinColumns = @JoinColumn(name="idEvent"),inverseJoinColumns=@JoinColumn(name="idSponsor"))
private List<Sponsor> sponsor;
}
