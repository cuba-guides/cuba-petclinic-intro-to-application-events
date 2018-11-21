package com.haulmont.sample.petclinic.configuration;

import com.haulmont.cuba.core.config.Config;
import com.haulmont.cuba.core.config.Property;
import com.haulmont.cuba.core.config.Source;
import com.haulmont.cuba.core.config.SourceType;
import com.haulmont.cuba.core.global.Configuration;
import com.haulmont.cuba.core.global.Secret;

@Source(type = SourceType.DATABASE)
public interface TwilioConfiguration extends Config {

    @Property("petclinic.notification.twilio.fromPhoneNumber")
    String getFromPhoneNumber();

    @Property("petclinic.notification.twilio.accountSid")
    String getAccountSid();

    @Property("petclinic.notification.twilio.authToken")
    @Secret
    String getAuthToken();

}
