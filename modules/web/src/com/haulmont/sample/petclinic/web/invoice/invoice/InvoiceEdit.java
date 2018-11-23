package com.haulmont.sample.petclinic.web.invoice.invoice;

import com.haulmont.cuba.gui.screen.EditedEntityContainer;
import com.haulmont.cuba.gui.screen.StandardEditor;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;
import com.haulmont.sample.petclinic.entity.invoice.Invoice;


@UiController("petclinic_Invoice.edit")
@UiDescriptor("invoice-edit.xml")
@EditedEntityContainer("invoiceDc")
public class InvoiceEdit extends StandardEditor<Invoice> {
}