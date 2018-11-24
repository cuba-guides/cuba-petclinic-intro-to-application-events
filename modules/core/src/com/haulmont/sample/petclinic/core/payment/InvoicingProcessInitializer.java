package com.haulmont.sample.petclinic.core.payment;


import com.google.common.collect.Lists;
import com.haulmont.cuba.core.app.UniqueNumbersAPI;
import com.haulmont.cuba.core.global.CommitContext;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.sample.petclinic.core.visit.VisitCompletedEvent;
import com.haulmont.sample.petclinic.entity.invoice.Invoice;
import com.haulmont.sample.petclinic.entity.invoice.InvoiceItem;
import com.haulmont.sample.petclinic.entity.visit.Visit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;

@Component("petclinic_invoicingProcessInitializer")
public class InvoicingProcessInitializer implements ApplicationListener<VisitCompletedEvent> {


    private static final Logger log = LoggerFactory.getLogger(InvoicingProcessInitializer.class);

    @Inject
    private DataManager dataManager;

    @Inject
    private UniqueNumbersAPI uniqueNumbersAPI;

    @Override
    public void onApplicationEvent(VisitCompletedEvent event) {
        log.info("Payment process initialized: {}", event.getSource());

        CommitContext commitContext = new CommitContext();
        createInvoiceFor(event.getSource(), commitContext);

        dataManager.commit(commitContext);
    }

    private void createInvoiceFor(Visit visit, CommitContext commitContext) {
        Invoice invoice = dataManager.create(Invoice.class);

        invoice.setVisit(visit);
        invoice.setInvoiceDate(visit.getVisitDate());
        invoice.setInvoiceNumber(createInvoiceNumber());

        List<InvoiceItem> invoiceItems = createInvoiceItemsFor(invoice);
        invoice.setItems(invoiceItems);

        invoiceItems.forEach(commitContext::addInstanceToCommit);
        commitContext.addInstanceToCommit(invoice);

    }

    private List<InvoiceItem> createInvoiceItemsFor(Invoice invoice) {
        InvoiceItem invoiceItem = dataManager.create(InvoiceItem.class);

        invoiceItem.setInvoice(invoice);
        invoiceItem.setPosition(1);
        invoiceItem.setText("Visit flat fee");
        invoiceItem.setPrice(new BigDecimal("150.0"));

        return Lists.newArrayList(invoiceItem);
    }

    private String createInvoiceNumber() {
        return String.format("%04d", uniqueNumbersAPI.getNextNumber("vists"));
    }


}
