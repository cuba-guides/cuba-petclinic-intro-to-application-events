package com.haulmont.sample.petclinic.web.visit.visit;

import com.haulmont.cuba.gui.events.UiEvent;
import org.springframework.context.ApplicationEvent;


public class VisitCompletedClickedEvent extends ApplicationEvent implements UiEvent {

    public VisitCompletedClickedEvent(Object source) {
        super(source);
    }
}
