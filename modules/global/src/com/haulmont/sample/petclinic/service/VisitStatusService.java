package com.haulmont.sample.petclinic.service;

import com.haulmont.sample.petclinic.entity.visit.Visit;

public interface VisitStatusService {
    String NAME = "petclinic_VisitStatusService";

    public boolean completeVisit(Visit visit);
}