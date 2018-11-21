package com.haulmont.sample.petclinic.entity.clinic;

import com.haulmont.sample.petclinic.entity.NamedEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Table(name = "PETCLINIC_ROOM")
@Entity(name = "petclinic_Room")
public class Room extends NamedEntity {
    @NotNull
    @Column(name = "ROOM_NUMBER", nullable = false)
    protected String roomNumber;

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }
}