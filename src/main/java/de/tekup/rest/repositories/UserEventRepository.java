package de.tekup.rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import de.tekup.rest.models.Evenement;
import de.tekup.rest.models.UserEvent;
import de.tekup.rest.models.UserEventId;

public interface UserEventRepository extends JpaRepository<UserEvent,UserEventId> {

}
