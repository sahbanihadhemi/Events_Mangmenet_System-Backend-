package de.tekup.rest.models;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
@Entity
@Data
public class UserEvent {

	

    @EmbeddedId
    private UserEventId id = new UserEventId();
    @JsonIgnore

    @ManyToOne
    @MapsId("eventId")
    private Evenement event;
    @JsonIgnore
    @ManyToOne
    @MapsId("userId")
    private User user;
 
    private char typeUser;
}
