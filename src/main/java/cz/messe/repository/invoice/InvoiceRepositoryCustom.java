package cz.messe.repository.invoice;

import java.util.List;

import cz.messe.model.customer.Customer;
import cz.messe.model.order.Order;

public interface InvoiceRepositoryCustom {

    /**
     * Return list of orders for invoicing for selected customer. Delivered and
     * non-invoiced routes will be returned.
     * 
     * @param customer customer
     * 
     * @return list of orders for invoicing
     */
    List<Order> getOrdersForInvoicingByCustomer(Customer customer);
}
