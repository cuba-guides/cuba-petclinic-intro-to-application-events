package com.haulmont.sample.petclinic.web.clinic.room;

import com.haulmont.cuba.gui.screen.*;
import com.haulmont.sample.petclinic.entity.clinic.Room;


@UiController("petclinic_Room.browse")
@UiDescriptor("room-browse.xml")
@LookupComponent("roomsTable")
public class RoomBrowse extends StandardLookup<Room> {
}