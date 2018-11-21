package com.haulmont.sample.petclinic.core.notification;

public interface MobilePhoneNotificationGateway {

    String NAME = "petclinic_mobilePhoneNotificationGateway";

    void sendNotification(String phoneNumber, String text);
}
