package cz.messe.repository.invoice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cz.messe.model.invoice.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long>{ 
    
}