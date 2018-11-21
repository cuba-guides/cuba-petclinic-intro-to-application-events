package com.haulmont.sample.petclinic.core.keycode;


import com.haulmont.cuba.core.app.events.EntityChangedEvent;
import com.haulmont.cuba.core.entity.contracts.Id;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Messages;
import com.haulmont.sample.petclinic.core.notification.MobilePhoneNotificationGateway;
import com.haulmont.sample.petclinic.entity.visit.Visit;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.inject.Inject;
import java.util.UUID;

@Component("petclinic_roomKeycodeToCustomerSender")
public class RoomKeycodeToCustomerSender {

    @Inject
    private DataManager dataManager;

    @Inject
    Messages messages;

    @Inject
    private MobilePhoneNotificationGateway mobilePhoneNotificationGateway;

    @TransactionalEventListener
    public void sendRoomKeycode(EntityChangedEvent<Visit, UUID> event) {

        if (event.getType().equals(EntityChangedEvent.Type.CREATED)) {
            Visit visit = loadVisit(event.getEntityId());
            tryToSendRoomKeycodeToPetsOwner(visit);
        }
    }

    private Visit loadVisit(Id<Visit, UUID> visitId) {
        return dataManager
                .load(visitId)
                .view("visit-with-room-and-pet-and-owner")
                .one();
    }

    private void tryToSendRoomKeycodeToPetsOwner(Visit visit) {

        String phoneNumber = visit.getPet().getOwner().getTelephone();
        if (visit.getPet().getOwner() != null && phoneNumber != null) {

            String notificationText = createNotificationText(visit);

            mobilePhoneNotificationGateway.sendNotification(phoneNumber, notificationText);
        }

    }

    private String createNotificationText(Visit visit) {
        return messages.formatMessage(
                this.getClass(),
                "roomKeycodeNotification",
                visit.getPet().getName(),
                visit.getRoom().getRoomNumber(),
                visit.getRoom().getName(),
                visit.getRoomKeycode()
        );
    }


}
