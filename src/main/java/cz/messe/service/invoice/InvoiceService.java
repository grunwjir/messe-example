package cz.messe.service.invoice;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.messe.model.customer.Customer;
import cz.messe.model.invoice.Invoice;
import cz.messe.repository.invoice.InvoiceRepository;

@Transactional
@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;
    
    @Autowired
    private InvoiceSolver invoiceSolver;
    
    public Optional<Invoice> getInvoice(Long id) {
        return invoiceRepository.findById(id);
    }
    
    public Page<Invoice> getAllInvoices(Pageable pageable) {
        return invoiceRepository.findAll(pageable);
    }
    
    public Long createInvoice(Customer customer) {
        Invoice invoice = invoiceSolver.createInvoice(customer);
        
        return invoice.getId();
    }
    
    public void updateInvoice(Invoice invoice) {
        invoiceSolver.updateInvoice(invoice);
    } 
    
}
