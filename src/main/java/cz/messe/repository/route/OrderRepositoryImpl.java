package cz.messe.repository.route;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cz.messe.model.order.OrderState;

@Repository
@Transactional
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
