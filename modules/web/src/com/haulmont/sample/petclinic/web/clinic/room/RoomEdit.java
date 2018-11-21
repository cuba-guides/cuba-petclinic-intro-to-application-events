package com.haulmont.sample.petclinic.web.clinic.room;

import com.haulmont.cuba.gui.screen.EditedEntityContainer;
import com.haulmont.cuba.gui.screen.StandardEditor;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;
import com.haulmont.sample.petclinic.entity.clinic.Room;


@UiController("petclinic_Room.edit")
@UiDescriptor("room-edit.xml")
@EditedEntityContainer("roomDc")
public class RoomEdit extends StandardEditor<Room> {
}