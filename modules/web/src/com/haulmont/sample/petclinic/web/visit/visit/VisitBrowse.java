package com.haulmont.sample.petclinic.web.visit.visit;

import com.haulmont.cuba.core.global.Events;
import com.haulmont.cuba.core.global.Messages;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.sample.petclinic.entity.visit.Visit;
import com.haulmont.sample.petclinic.service.VisitStatusService;
import org.springframework.context.event.EventListener;

import javax.inject.Inject;

@UiController("petclinic_Visit.browse")
@UiDescriptor("visit-browse.xml")
@LookupComponent("visitsTable")
public class VisitBrowse extends StandardLookup<Visit> {

    @Inject
    private VisitStatusService visitStatusService;

    @Inject
    private GroupTable<Visit> visitsTable;

    @Inject
    private Notifications notifications;

    @Inject
    private CollectionContainer<Visit> visitsCt;

    @Inject
    private CollectionLoader<Visit> visitsLd;

    @Inject
    private Messages messages;

    @Inject
    private Events events;


    @Subscribe("visitsTable.completeVisit")
    protected void completeVisit(Action.ActionPerformedEvent event) {
        Visit visit = visitsTable.getSingleSelected();
        boolean visitWasCompleted = visitStatusService.completeVisit(visit);

        if (visitWasCompleted) {
            events.publish(new VisitCompletedClickedEvent(this));
        }
        else {
            notifications.create()
                    .setCaption(messages.formatMessage(this.getClass(), "visitCompleteNotSuccessful"))
                    .setType(Notifications.NotificationType.ERROR)
                    .show();
        }
    }
    

    @EventListener
    protected void updateDataOnVisitCompleted(VisitCompletedClickedEvent event) {
        loadData();

        notifications.create()
                .setCaption(messages.formatMessage(this.getClass(), "visitCompleteSuccessful"))
                .setType(Notifications.NotificationType.TRAY)
                .show();
    }

}