package de.tekup.rest.models;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

import lombok.Data;
@Embeddable
@Data
public class UserEventId implements Serializable{
private static final long serialVersionUID = 1L;

	private Long userId;
    private Long eventId;
    public UserEventId()
    {}
    public UserEventId(Long userId, Long eventId)
    {
    	super();
    	this.eventId=eventId;
    	this.userId=userId;
    }
    
 @Override
 public int hashCode()
 {
	 
	 final int prime = 31;
     int result = 1;
     result = prime * result
             + ((userId == null) ? 0 : userId.hashCode());
     result = prime * result
             + ((eventId == null) ? 0 : eventId.hashCode());
     return result;
 }
 @Override
public boolean equals(Object obj) {
	 if (this == obj)
         return true;
     if (obj == null)
         return false;
     if (getClass() != obj.getClass())
         return false;
     UserEventId other = (UserEventId) obj;
     return Objects.equals(getUserId(), other.getUserId()) && Objects.equals(getEventId(), other.getEventId());
 }
 }

