package de.tekup.rest.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.tekup.rest.models.Evenement;
import de.tekup.rest.models.User;
import de.tekup.rest.models.UserEvent;
import de.tekup.rest.repositories.EventRepository;
import de.tekup.rest.repositories.UserEventRepository;
import de.tekup.rest.repositories.UserRepository;
@Service
public class UserService {
	private UserRepository reposUser;
	private EventRepository reposEvent;
	private UserEventRepository UER;
	@Autowired
public UserService(UserRepository reposUser, EventRepository reposEvent, UserEventRepository UER) {
		super();
		this.reposUser = reposUser;
		this.reposEvent=reposEvent;
		this.UER=UER;
	}
// add user
	public User addUser(User e)
	{return this.reposUser.save(e);
		}
// all user
public List<User> allUser()
{return (List<User>) this.reposUser.findAll();
}
//find User by id 
	public User getUserById(long id) {
		Optional<User> opt = reposUser.findById(id);
		User entity;
		if(opt.isPresent())
			entity= opt.get();
		else
			throw new NoSuchElementException("User with this Id is not found");
		return entity;
	}
//find event by id 
	public Evenement getEntityById(long id) {
		Optional<Evenement> opt = reposEvent.findById(id);
		Evenement entity;
		if(opt.isPresent())
			entity= opt.get();
		else
			throw new NoSuchElementException("events with this Id is not found");
		return entity;
	}

// inscrit au event
public UserEvent inscritEvent (Long idEvents,  Long idUser) {
	Evenement e = this.getEntityById(idEvents);
	User user=this.getUserById(idUser);
	UserEvent UE = new UserEvent();
	UE.setEvent(e);
	UE.setUser(user);
	UE.setTypeUser('p');
	e.getUserEvents().add(UE);
    user.getUserEvents().add(UE);
    
return this.UER.save(UE);

}
//get el event eli howa inscrit fihoum
public List<Evenement> participedin(Long idUser)

{	User user=this.getUserById(idUser);
List<UserEvent> listUserEvent = new ArrayList<>();
List<Evenement> listEvent = new ArrayList<>();

listUserEvent.addAll(user.getUserEvents()) ;
for(UserEvent UE : listUserEvent)
{
	if(UE.getTypeUser()=='p')
listEvent.add(UE.getEvent());

}
return listEvent;
	}
//get el event eli howa 3amelhoum
public List<Evenement> getMyEvent(Long idUser)

{	User user=this.getUserById(idUser);
List<UserEvent> listUserEvent = new ArrayList<>();
List<Evenement> listEvent = new ArrayList<>();

listUserEvent.addAll(user.getUserEvents()) ;
for(UserEvent UE : listUserEvent)
{
	if(UE.getTypeUser()=='o')
listEvent.add(UE.getEvent());

}
return listEvent;
	}
}