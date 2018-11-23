alter table PETCLINIC_INVOICE add column INVOICE_NUMBER varchar(255) ^
update PETCLINIC_INVOICE set INVOICE_NUMBER = '' where INVOICE_NUMBER is null ;
alter table PETCLINIC_INVOICE alter column INVOICE_NUMBER set not null ;
