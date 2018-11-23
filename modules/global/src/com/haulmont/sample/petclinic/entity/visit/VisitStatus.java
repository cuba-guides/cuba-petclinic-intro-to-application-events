package com.haulmont.sample.petclinic.entity.visit;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum VisitStatus implements EnumClass<String> {

    BOOKED("BOOKED"),
    ACTIVE("ACTIVE"),
    COMPLETED("COMPLETED");

    private String id;

    VisitStatus(String value) {
        this.id = value;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static VisitStatus fromId(String id) {
        for (VisitStatus at : VisitStatus.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}