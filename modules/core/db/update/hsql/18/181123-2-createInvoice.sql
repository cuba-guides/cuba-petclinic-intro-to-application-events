alter table PETCLINIC_INVOICE add constraint FK_PETCLINIC_INVOICE_ON_VISIT foreign key (VISIT_ID) references PETCLINIC_VISIT(ID);
create index IDX_PETCLINIC_INVOICE_ON_VISIT on PETCLINIC_INVOICE (VISIT_ID);