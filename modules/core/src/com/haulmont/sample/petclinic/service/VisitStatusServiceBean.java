package com.haulmont.sample.petclinic.service;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Events;
import com.haulmont.sample.petclinic.core.visit.VisitCompletedEvent;
import com.haulmont.sample.petclinic.entity.visit.Visit;
import com.haulmont.sample.petclinic.entity.visit.VisitStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service(VisitStatusService.NAME)
public class VisitStatusServiceBean implements VisitStatusService {


    private static final Logger log = LoggerFactory.getLogger(VisitStatusServiceBean.class);

    @Inject
    private Events events;

    @Inject
    private DataManager dataManager;

    @Override
    public boolean completeVisit(Visit visit) {

        if (visit.getStatus().equals(VisitStatus.ACTIVE)) {
            markVisitAsComplete(visit);

            notifyAboutVisitCompletion(visit);

            return true;
        }
        else {
            log.warn("Visit {} is not active. Mark as complete is not possible", visit);
            return false;
        }
    }

    private void notifyAboutVisitCompletion(Visit visit) {
        events.publish(new VisitCompletedEvent(this, visit));
    }

    private void markVisitAsComplete(Visit visit) {
        visit.setStatus(VisitStatus.COMPLETED);
        dataManager.commit(visit);
        log.info("Visit {} marked as complete", visit);
    }
}