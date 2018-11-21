package com.haulmont.sample.petclinic.core.keycode;


import com.haulmont.cuba.core.app.events.EntityChangedEvent;
import com.haulmont.cuba.core.entity.contracts.Id;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.sample.petclinic.core.room_system.RoomSystemGateway;
import com.haulmont.sample.petclinic.entity.visit.Visit;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.inject.Inject;
import java.util.UUID;

@Component("petclinic_roomSystemNotifier")
public class RoomSystemNotifier {

    @Inject
    private DataManager dataManager;

    @Inject
    RoomSystemGateway roomSystemGateway;

    @TransactionalEventListener
    public void notifyRoomSystem(EntityChangedEvent<Visit, UUID> event) {

        if (event.getType().equals(EntityChangedEvent.Type.CREATED)) {
            Visit visit = loadVisit(event.getEntityId());
            tryToNotifyRoomSystemAboutVisit(visit);
        }
    }

    private Visit loadVisit(Id<Visit, UUID> visitId) {
        return dataManager
                .load(visitId)
                .view("visit-with-room-and-pet-and-owner")
                .one();
    }

    private void tryToNotifyRoomSystemAboutVisit(Visit visit) {
        roomSystemGateway.informAboutVisit(visit);
    }

}
