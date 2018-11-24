package com.haulmont.sample.petclinic.core.visit;

import com.haulmont.sample.petclinic.entity.visit.Visit;
import org.springframework.context.ApplicationEvent;

public class VisitCompletedEvent extends ApplicationEvent {

    public VisitCompletedEvent(Visit source) {
        super(source);
    }

    @Override
    public Visit getSource() {
        return (Visit) super.getSource();
    }
}
