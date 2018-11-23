package com.haulmont.sample.petclinic.entity.invoice;

import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.Lookup;
import com.haulmont.cuba.core.entity.annotation.LookupType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Table(name = "PETCLINIC_INVOICE_ITEM")
@Entity(name = "petclinic_InvoiceItem")
public class InvoiceItem extends StandardEntity {
    @Lookup(type = LookupType.DROPDOWN, actions = {"lookup", "open"})
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "INVOICE_ID")
    protected Invoice invoice;
    @NotNull
    @Column(name = "POSITION", nullable = false)
    protected Integer position;
    @NotNull
    @Column(name = "TEXT", nullable = false)
    protected String text;
    @NotNull
    @Column(name = "PRICE", nullable = false)
    protected BigDecimal price;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }
}