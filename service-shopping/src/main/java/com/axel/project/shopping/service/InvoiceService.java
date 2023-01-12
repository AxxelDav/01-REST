package com.axel.project.shopping.service;

import com.axel.project.shopping.entity.Invoice;

import java.util.List;

public interface InvoiceService {
    public Invoice getInvoice(Long id);
    public List<Invoice> findInvoiceAll();
    public Invoice createInvoice(Invoice invoice);
    public Invoice updateInvoice(Invoice invoice);
    public Invoice deleteInvoice(Invoice invoice);

}
