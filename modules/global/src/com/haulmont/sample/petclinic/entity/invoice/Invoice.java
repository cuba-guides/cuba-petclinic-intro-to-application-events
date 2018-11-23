package com.haulmont.sample.petclinic.entity.invoice;

import com.haulmont.chile.core.annotations.Composition;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.Lookup;
import com.haulmont.cuba.core.entity.annotation.LookupType;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;
import com.haulmont.sample.petclinic.entity.visit.Visit;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Table(name = "PETCLINIC_INVOICE")
@Entity(name = "petclinic_Invoice")
public class Invoice extends StandardEntity {
    @NotNull
    @Column(name = "INVOICE_NUMBER", nullable = false)
    protected String invoiceNumber;
    @Temporal(TemporalType.DATE)
    @NotNull
    @Column(name = "INVOICE_DATE", nullable = false)
    protected Date invoiceDate;
    @Lookup(type = LookupType.DROPDOWN, actions = {"lookup", "open"})
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "VISIT_ID")
    protected Visit visit;
    @OrderBy("position")
    @Composition
    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "invoice")
    protected List<InvoiceItem> items;

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public List<InvoiceItem> getItems() {
        return items;
    }

    public void setItems(List<InvoiceItem> items) {
        this.items = items;
    }

    public Visit getVisit() {
        return visit;
    }

    public void setVisit(Visit visit) {
        this.visit = visit;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }
}