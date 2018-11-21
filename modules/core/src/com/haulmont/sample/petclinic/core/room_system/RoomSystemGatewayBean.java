package com.haulmont.sample.petclinic.core.room_system;


import com.haulmont.sample.petclinic.entity.visit.Visit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component(RoomSystemGateway.NAME)
public class RoomSystemGatewayBean implements RoomSystemGateway {


    private static final Logger log = LoggerFactory.getLogger(RoomSystemGatewayBean.class);


    @Override
    public void informAboutVisit(Visit visit) {

        log.info("Sending visit to the Room System to notify about the room keycode for a given day of the visit");
    }


}
