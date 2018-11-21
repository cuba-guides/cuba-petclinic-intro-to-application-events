package com.haulmont.sample.petclinic.core.notification;


import com.haulmont.sample.petclinic.configuration.TwilioConfiguration;
import com.twilio.Twilio;
import com.twilio.exception.TwilioException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component(MobilePhoneNotificationGateway.NAME)
public class TwilioNotificationGateway implements MobilePhoneNotificationGateway {


    private static final Logger log = LoggerFactory.getLogger(TwilioNotificationGateway.class);


    @Inject
    private TwilioConfiguration twilioConfiguration;

    @Override
    public void sendNotification(String phoneNumber, String text) {

        initTwilioClient();

        try {

            Message message = Message.creator(
                    new PhoneNumber(phoneNumber),
                    new PhoneNumber(twilioConfiguration.getFromPhoneNumber()),
                    text
            ).create();


            Message.Status messageStatus = message.getStatus();
            if (isDeliveredSuccessfully(messageStatus)) {
                log.info("Notification delivered successfully to {} (Message Status: {}). Twilio Reference ID: {}", phoneNumber, messageStatus, message.getSid());
            }
            else {
                log.error("Notification could not be send to " +  phoneNumber +  " via Twilio. Message Status: ", messageStatus);
            }

        } catch (TwilioException e) {
            log.error("Notification could not be send to " +  phoneNumber +  " via Twilio.", e);
        }
    }

    private void initTwilioClient() {
        Twilio.init(twilioConfiguration.getAccountSid(), twilioConfiguration.getAuthToken());
    }

    private boolean isDeliveredSuccessfully(Message.Status messageStatus) {
        return messageStatus.equals(Message.Status.ACCEPTED) ||
                messageStatus.equals(Message.Status.DELIVERED) ||
                messageStatus.equals(Message.Status.QUEUED) ||
                messageStatus.equals(Message.Status.SENDING) ||
                messageStatus.equals(Message.Status.SENT) ||
                messageStatus.equals(Message.Status.DELIVERED) ||
                messageStatus.equals(Message.Status.RECEIVING) ||
                messageStatus.equals(Message.Status.RECEIVED) ||
                messageStatus.equals(Message.Status.ACCEPTED);

    }

}
