package com.haulmont.sample.petclinic.web.invoice.invoice;

import com.haulmont.cuba.gui.screen.*;
import com.haulmont.sample.petclinic.entity.invoice.Invoice;


@UiController("petclinic_Invoice.browse")
@UiDescriptor("invoice-browse.xml")
@LookupComponent("invoicesTable")
@LoadDataBeforeShow
public class InvoiceBrowse extends StandardLookup<Invoice> {
}