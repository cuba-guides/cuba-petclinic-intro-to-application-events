package com.haulmont.sample.petclinic.web.visit.visit;

import com.haulmont.cuba.gui.screen.*;
import com.haulmont.cuba.gui.components.TextField;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.sample.petclinic.entity.visit.Visit;

import javax.inject.Inject;
import java.util.Random;


@UiController("petclinic_Visit.edit")
@UiDescriptor("visit-edit.xml")
@EditedEntityContainer("visitCt")
@LoadDataBeforeShow
public class VisitEdit extends StandardEditor<Visit> {

    @Inject
    TextField roomKeycodeTextField;

    @Subscribe
    protected void onInitEntity(InitEntityEvent<Visit> event) {
        event.getEntity().setRoomKeycode(generateRoomKeycode());
    }

    private String generateRoomKeycode() {
        int rookKeycode = new Random().nextInt(999999);
        return String.format("%04d", rookKeycode);
    }

}