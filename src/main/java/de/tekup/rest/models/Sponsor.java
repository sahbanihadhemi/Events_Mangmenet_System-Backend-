package de.tekup.rest.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Sponsor {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idSponsor;
	
	private String nom;
	
	private String Img;
	   @ManyToMany(mappedBy = "sponsor")
	    private List<Evenement> events;
}
