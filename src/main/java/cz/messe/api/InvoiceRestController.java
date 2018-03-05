package cz.messe.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cz.messe.model.invoice.Invoice;
import cz.messe.service.invoice.InvoiceService;

@RestController
@RequestMapping("invoices")
public class InvoiceRestController {
     
    @Autowired
    private InvoiceService invoiceService;
    
    @GetMapping("/{id}")
    public Invoice getInvoice(@PathVariable("id") Long id) {
        Optional<Invoice> invoice = invoiceService.getInvoice(id);
        
        if (!invoice.isPresent()) {
            throw new ResourceNotFoundException();
        }
            
        return invoice.get();
    }
 
    @GetMapping
    public Page<Invoice> getAllInvoices(
            @RequestParam(value = "page") Integer page, 
            @RequestParam(value = "size") Integer size) {
        return invoiceService.getAllInvoices(PageRequest.of(page, size));
    }
    
    @PutMapping("/{id}")
    public void updateInvoice(@PathVariable("id") Long id) {
        Optional<Invoice> updatingInvoice = invoiceService.getInvoice(id);
              
        if (!updatingInvoice.isPresent()) {
            throw new ResourceNotFoundException();
        }
        
        invoiceService.updateInvoice(updatingInvoice.get());
    }
    
}