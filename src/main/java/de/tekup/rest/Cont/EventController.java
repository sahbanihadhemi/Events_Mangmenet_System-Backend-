package de.tekup.rest.Cont;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import de.tekup.rest.models.Email;
import de.tekup.rest.models.Evenement;
import de.tekup.rest.models.Sponsor;
import de.tekup.rest.models.User;
import de.tekup.rest.models.UserEvent;
import de.tekup.rest.services.EmailSender;
import de.tekup.rest.services.EventService;
import de.tekup.rest.services.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")

public class EventController {
	private EventService eService;
	private UserService Uservice;
	private EmailSender emailService;
	@Autowired
	public EventController(EmailSender emailService,EventService eService, UserService Uservice)
	{
       super();
       this.eService=eService;
       this.Uservice=Uservice;
       this.emailService=emailService;
}
	
	
// afficher tous les events
	@GetMapping("/all")
	public List<Evenement> getAll(){
		return eService.afficherTousLesEvents();
	}
	
// get all user
	@GetMapping("/allUser")
	public List<User> getAllUser(){
		return Uservice.allUser();
	}
	
// add user
	@PostMapping("/adduser")
	public User addUser(@RequestBody User e)
	{
		return Uservice.addUser(e);
		
	}
	
//supprimer un event
	@GetMapping("/deleteEvent/{id}")
	public Evenement deleteEvents(@PathVariable(name = "id") Long id) {
	    return eService.deleteEventById(id);      
	}
	//sendEmail
	@PostMapping("/sendEmail")
	public void sendEmail(@RequestBody Email e)
	{
	this.emailService.sendEmail(e.getToEmail(),e.getSubject(),e.getBody());
	}



// ajouter event
	@PostMapping("/test")
			public Evenement ajouterEvent(@RequestBody Evenement e)
	{
		return eService.ajouterEvent(e);
	}
	
// get event by id
	@GetMapping("/eventById/{id}")
	public Evenement getEventById(@PathVariable(name = "id") Long id)
	{
		return eService.getEntityById(id);
	} 
// inscrit au event
	@GetMapping("/inscrit/{idE}/{idU}")
	public UserEvent inscritEvent(@PathVariable(name="idE") Long idE, @PathVariable(name= "idU") Long idU)
	{
		return Uservice.inscritEvent(idE, idU);}
	//++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	@GetMapping("/getMyEvent/{idE}")

	public List<Evenement> getUserInscrit(@PathVariable(name="idE") Long idE)
	{
		return Uservice.participedin(idE);
	}
	//++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	@GetMapping("/getMyEvent2/{idE}")

	public List<Evenement> getMyEvent(@PathVariable(name="idE") Long idE)
	{
		return Uservice.getMyEvent(idE);
	}
// get sponsor
	@GetMapping("sponsor/{idE}")
	public List<Sponsor> getSponsor(@PathVariable(name="idE") Long idE)
	{
		return eService.getSponsor(idE);	
	}
	//contact Us
	@PostMapping("/contactUs")
	public void contactUs(@RequestBody Email e)
	{
	this.emailService.contactUs(e.getFromEmail(),e.getSubject(),e.getBody());
	}

}
