package com.haulmont.sample.petclinic.core.room_system;

import com.haulmont.sample.petclinic.entity.visit.Visit;

public interface RoomSystemGateway {

    String NAME = "petclinic_roomSystemGateway";

    void informAboutVisit(Visit visit);
}
