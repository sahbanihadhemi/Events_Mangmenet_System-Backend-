package de.tekup.rest.services;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CharArrayPropertyEditor;
import org.springframework.stereotype.Service;

import de.tekup.rest.models.Evenement;
import de.tekup.rest.models.Sponsor;
import de.tekup.rest.models.User;
import de.tekup.rest.repositories.EventRepository;
import de.tekup.rest.repositories.UserRepository;
@Service
public class EventService {
	private EventRepository reposEvent;
	private UserRepository reposUser;
	@Autowired
public EventService(EventRepository reposEvent,UserRepository reposUser) {
		super();
		this.reposEvent = reposEvent;
		this.reposUser=reposUser;
		
	}
	/* Afficher tous les events */
	public List<Evenement> afficherTousLesEvents(){
		return reposEvent.findAll();
	}
	// find event by id 
	public Evenement getEntityById(long id) {
		Optional<Evenement> opt = reposEvent.findById(id);
		Evenement entity;
		if(opt.isPresent())
		{
			entity= opt.get();
			String dayD= String.valueOf(entity.getDateDebut().getDayOfMonth());
			String monthD=entity.getDateDebut().getMonth().toString();
			String yearD= String.valueOf(entity.getDateDebut().getYear());
		entity.setDateDebutString(dayD+"-"+monthD+"-"+yearD);
		String dayF= String.valueOf(entity.getDateFin().getDayOfMonth());
		String monthF=entity.getDateFin().getMonth().toString();
		String yearF= String.valueOf(entity.getDateFin().getYear());
	entity.setDateDebutString(dayF+"-"+monthF+"-"+yearF);
	LocalTime t1= entity.getDateDebut().toLocalTime();
	LocalTime t2= entity.getDateFin().toLocalTime();
	entity.setDebut(t1);
	entity.setFin(t2);
		}
		else
			throw new NoSuchElementException("events with this Id is not found");
		return entity;
	}


	
	/*Ajouter un events */
	public Evenement ajouterEvent(Evenement e)
	{return this.reposEvent.save(e);
		}

	/* modifier event */
	public Evenement modifierEvents(long id, Evenement newEntity) {
		Evenement entity = this.getEntityById(id);
		if(newEntity.getNom() != null)
			entity.setNom(newEntity.getNom());
		if(newEntity.getCategorie() != null)
			entity.setCategorie(newEntity.getCategorie());
		
		return reposEvent.save(entity);
	}

	public Evenement deleteEventById(long id) {
		Evenement entity = this.getEntityById(id);
		reposEvent.deleteById(id);
		return entity;
	}
	public String index() {
	    return "index";
	 }
	

	// s'incrire au event : 
	public User getUserById(Long id) {
		Optional<User> opt = reposUser.findById(id);
		User entity;
		if(opt.isPresent())
			entity= opt.get();
		else
			throw new NoSuchElementException("user with this Id is not found");
		return entity;
	}


	
 // get Sponsor
	

public List<Sponsor> getSponsor(Long id)
{		Evenement evenement = this.getEntityById(id);
        List<Sponsor> listSponsor = new ArrayList<>();
        listSponsor= evenement.getSponsor();
	return listSponsor;}
	
}
