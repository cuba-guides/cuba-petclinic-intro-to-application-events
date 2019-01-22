package com.haulmont.sample.petclinic.web.invoice.invoice;

import com.haulmont.cuba.core.global.MetadataTools;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.sample.petclinic.entity.invoice.Invoice;

import javax.inject.Inject;


@UiController("petclinic_Invoice.edit")
@UiDescriptor("invoice-edit.xml")
@EditedEntityContainer("invoiceDc")
@LoadDataBeforeShow
public class InvoiceEdit extends StandardEditor<Invoice> {

    @Inject
    private MessageBundle messageBundle;

    @Inject
    private MetadataTools metadataTools;


    @Subscribe
    protected void onBeforeShow(BeforeShowEvent event) {
        getWindow().setCaption(createCaption());
    }

    private String createCaption() {
        return messageBundle.formatMessage("editorCaption", metadataTools.getInstanceName(getEditedEntity()));
    }
}