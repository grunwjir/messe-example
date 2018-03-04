package cz.messe.repository.route;

import cz.messe.model.order.OrderState;

public interface OrderRepositoryCustom {

    void updateOrderState(Long orderId, OrderState state);
    
}
