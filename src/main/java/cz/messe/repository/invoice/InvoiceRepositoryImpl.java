package cz.messe.repository.invoice;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cz.messe.model.customer.Customer;
import cz.messe.model.order.Order;
import cz.messe.model.order.OrderState;

@Repository
@Transactional
public class InvoiceRepositoryImpl implements InvoiceRepositoryCustom {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<Order> getOrdersForInvoicingByCustomer(Customer customer) {
        TypedQuery<Order> query = em.createQuery("SELECT r FROM Order r WHERE r.state = :state AND NOT EXISTS "
                + "(SELECT 1 FROM InvoicedOrder ir WHERE ir.order.id = r.id)", Order.class)
            .setParameter("state", OrderState.CLOSED);
                
        return query.getResultList();
    }

}
