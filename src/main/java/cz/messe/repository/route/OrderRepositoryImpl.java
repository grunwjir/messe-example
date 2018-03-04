package cz.messe.repository.route;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import cz.messe.model.order.OrderState;

@Repository
public class OrderRepositoryImpl implements OrderRepositoryCustom {

    @PersistenceContext
    EntityManager em;
    
    @Override
    public void updateOrderState(Long orderId, OrderState state) { 
        em.createQuery("UPDATE Order o SET o.state = :state WHERE o.id = :orderId")
            .setParameter("state", state)
            .setParameter("orderId", orderId)
            .executeUpdate();
    }

}
