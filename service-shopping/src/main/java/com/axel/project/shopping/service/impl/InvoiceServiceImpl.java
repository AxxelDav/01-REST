package com.axel.project.shopping.service.impl;

import com.axel.project.shopping.client.CustomerClient;
import com.axel.project.shopping.client.ProductClient;
import com.axel.project.shopping.entity.Invoice;
import com.axel.project.shopping.entity.InvoiceItem;
import com.axel.project.shopping.model.Customer;
import com.axel.project.shopping.model.Product;
import com.axel.project.shopping.repository.InvoiceItemsRepository;
import com.axel.project.shopping.repository.InvoiceRepository;
import com.axel.project.shopping.service.InvoiceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    InvoiceItemsRepository invoiceItemsRepository;

    @Autowired
    private CustomerClient customerClient;

    @Autowired
    private ProductClient productClient;

    @Override
    public Invoice getInvoice(Long id) {
        Invoice invoice = invoiceRepository.findById(id).orElse(null);
        if (invoice != null) {
            Customer customer = customerClient.getCustomer(invoice.getCustomerId()).getBody(); //Obtengo los datos del cliente (customer) de esta factura
            invoice.setCustomer(customer); //seteo el customer de la factura por el que cree en la linea 36
            List<InvoiceItem> listItem = invoice.getItems().stream().map(invoiceItem -> {
                 Product product = productClient.getProduct(invoiceItem.getProductId()).getBody();
                 invoiceItem.setProduct(product);
                 return invoiceItem;
            }).collect(Collectors.toList()); //Recupero cada Item y le seteo su producto.
            invoice.setItems(listItem);
        }
        return invoice;
    }

    @Override
    public List<Invoice> findInvoiceAll() {
        return invoiceRepository.findAll();
    }

    @Override
    public Invoice createInvoice(Invoice invoice) {
        Invoice invoiceDB = invoiceRepository.findByNumberInvoice(invoice.getNumberInvoice());
        if (invoiceDB != null)
            return invoiceDB;
        invoice.setState("CREATED");
        invoiceDB = invoiceRepository.save(invoice);
        invoiceDB.getItems().forEach(invoiceItem -> { productClient.updateStockProduct(invoiceItem.getProductId(), invoiceItem.getQuantity() * -1); }); //Actualizo el stock de los productos para cada uno de los items de la factura, restandole la cantidad del producto que estamos consumiendo en la factura.
        return invoiceDB;
    }

    @Override
    public Invoice updateInvoice(Invoice invoice) {
        Invoice invoiceDB = getInvoice(invoice.getId());
        if (invoiceDB == null)
            return null;
        invoiceDB.setCustomerId(invoice.getCustomerId());
        invoiceDB.setDescription(invoice.getDescription());
        invoiceDB.setNumberInvoice(invoice.getNumberInvoice());
        invoiceDB.getItems().clear();
        invoiceDB.setItems(invoice.getItems());
        return invoiceRepository.save(invoiceDB);
    }

    @Override
    public Invoice deleteInvoice(Invoice invoice) {
        Invoice invoiceDB = getInvoice(invoice.getId());
        if (invoiceDB == null)
            return null;
        invoiceDB.setState("DELETED");
        return invoiceRepository.save(invoiceDB);
    }


}
