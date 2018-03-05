package cz.messe.service.invoice;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cz.messe.model.customer.Customer;
import cz.messe.model.invoice.Invoice;
import cz.messe.model.invoice.InvoiceItem;
import cz.messe.model.invoice.InvoicedOrder;
import cz.messe.model.order.Order;
import cz.messe.repository.invoice.InvoiceRepository;
import cz.messe.service.Utils;

@Component
public class InvoiceSolver {
    
    public static final int INVOICE_DUE_DAYS = 14;
    public static final double TAX_COEFFICIENT = 1.21;
    public static final String COURIER_SERVICES_ITEM_NAME = "Courier services";
    
    @Autowired
    private InvoiceRepository invoiceRepository;
    
    @Autowired
    private Utils utils;
    
    public Invoice createInvoice(Customer customer) {
        Invoice invoice = new Invoice();
        LocalDate now = LocalDate.now();
        
        // set basic invoice parameters
        invoice.setCustomer(customer);
        invoice.setIssueDate(now);
        invoice.setDueDate(now.plusDays(INVOICE_DUE_DAYS));
        invoice.setPeriodName(resolvePeriodName(now));

        // select orders for invoicing
        List<Order> orders = invoiceRepository.getOrdersForInvoicingByCustomer(customer);
        
        // add routes to the invoice
        orders.stream()
            .map(InvoicedOrder::new)
            .forEach(invoice::addRoute);
                
        return updateInvoice(invoice);
    }
    
    public Invoice updateInvoice(Invoice invoice) {
        double discount = invoice.getCustomer().getDiscountCoefficient();
        
        // set order data to invoiced order for each one
        invoice.getRoutes().stream().forEach(invoicedRoute -> {
            Order source = invoicedRoute.getOrder();
            
            invoicedRoute.setPrice(source.getPrice());
            invoicedRoute.setPriceDiscounted(source.getPrice());
            invoicedRoute.setInsurance(source.getInsurance());
            invoicedRoute.setTownFrom(source.getTownFrom());
            invoicedRoute.setTownTo(source.getTownTo());
        });
        
        // remove calculated items - will be recreated
        invoice.getCalculatedItems().clear();
      
        // create new courier services item 
        invoice.addCalculatedItem(createCourierServiceItem(invoice, discount));

        // set total price
        setFinalPriceAmounts(invoice);
        
        return invoiceRepository.save(invoice);
    }
    
    private InvoiceItem createCourierServiceItem(Invoice invoice, double discount) {
        // compute total price of all orders
        BigDecimal totalPrice = invoice.getRoutes().stream()
                .map(InvoicedOrder::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        BigDecimal priceDiscounted = utils.getAmountDiscounted(totalPrice, discount);
        BigDecimal taxAmount = utils.getTaxAmount(priceDiscounted, TAX_COEFFICIENT);
        BigDecimal totalWithTax = priceDiscounted.add(taxAmount);
        
        // create invoice items
        InvoiceItem courierServices = new InvoiceItem(COURIER_SERVICES_ITEM_NAME + " - " + invoice.getPeriodName(),
                taxAmount, priceDiscounted, totalWithTax, TAX_COEFFICIENT);

        return courierServices;
    }
    
    private void setFinalPriceAmounts(Invoice invoice) {
        BigDecimal totalAmount = invoice.getCalculatedItems().stream()
                .map(InvoiceItem::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        BigDecimal totalTaxAmount = invoice.getCalculatedItems().stream()
                .map(InvoiceItem::getTaxAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        invoice.setAmount(totalAmount);
        invoice.setTaxAmount(totalTaxAmount);
        invoice.setAmountWithTax(totalAmount.add(totalTaxAmount));   
    }
    
    private String resolvePeriodName(LocalDate date) {
        return date.minusMonths(1)
                .getMonth()
                .getDisplayName(TextStyle.FULL, Locale.ENGLISH);
    }
        
}
