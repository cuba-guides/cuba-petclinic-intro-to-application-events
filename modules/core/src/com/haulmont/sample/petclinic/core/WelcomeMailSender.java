package com.haulmont.sample.petclinic.core;


import com.haulmont.cuba.core.app.EmailerAPI;
import com.haulmont.cuba.core.app.events.EntityChangedEvent;
import com.haulmont.cuba.core.entity.contracts.Id;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.EmailInfo;
import com.haulmont.sample.petclinic.entity.pet.Pet;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component("petclinic_welcomeMailSender")
public class WelcomeMailSender {

    @Inject
    private DataManager dataManager;

    @Inject
    private EmailerAPI emailerAPI;

    @TransactionalEventListener
    public void sendWelcomeMail(EntityChangedEvent<Pet, UUID> event) {

        if (event.getType().equals(EntityChangedEvent.Type.CREATED)) {
            Pet pet = loadPet(event.getEntityId());
            tryToSendEmailToPetsOwner(pet);
        }
    }

    private Pet loadPet(Id<Pet, UUID> petId) {
        return dataManager
                .load(petId)
                .view("pet-with-owner-and-type")
                .one();
    }

    private void tryToSendEmailToPetsOwner(Pet pet) {

        if (pet.getOwner() != null && pet.getOwner().getEmail() != null) {
            
            String emailSubject = "Welcome to the Petclinic, " + pet.getName();
            String ownerEmail = pet.getOwner().getEmail();

            EmailInfo email = new EmailInfo(
                    ownerEmail,
                    emailSubject,
                    null,
                    "com/haulmont/sample/petclinic/templates/welcome-email.tpl",
                    getTemplateParams(pet)
            );

            emailerAPI.sendEmailAsync(email);
        }

    }


    private Map<String, Serializable> getTemplateParams(Pet pet) {
        Map<String, Serializable> templateParameters = new HashMap<>();

        templateParameters.put("owner", pet.getOwner());
        templateParameters.put("pet", pet);
        return templateParameters;
    }

}
