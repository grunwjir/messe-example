package cz.messe.service.order;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.messe.model.order.Order;
import cz.messe.model.order.OrderState;
import cz.messe.repository.CustomerRepository;
import cz.messe.repository.route.OrderRepository;


@Transactional
@Service
public class OrderService {

    @Autowired
    private OrderRepository  orderRepository;
    
    @Autowired
    private CustomerRepository  customerRepository;
    
    public Optional<Order> getOrder(Long id) {
        return orderRepository.findById(id);
    }
    
    public Long createOrder(Order order) {
        order.setCustomer(customerRepository.getOne(order.getCustomer().getId()));
        
        Order savedOrder = orderRepository.save(order);
        
        return savedOrder.getId();
    }
    
    public void updateOrder(Order order) {
        Order updatingOrder = orderRepository.findById(order.getId()).get();
        
        updatingOrder.setTownFrom(order.getTownFrom());
        updatingOrder.setTownTo(order.getTownTo());
        updatingOrder.setPickup(order.getPickup());
        updatingOrder.setDelivery(order.getDelivery());
        updatingOrder.setProduct(order.getProduct());
        updatingOrder.setPrice(order.getPrice());
        updatingOrder.setInsurance(order.getInsurance());
        updatingOrder.setCustomer(customerRepository.getOne(order.getCustomer().getId()));
        updatingOrder.setState(order.getState());
        
        orderRepository.save(updatingOrder);
    }
    
    public void updateOrderState(Long orderId, OrderState state) {
        orderRepository.updateOrderState(orderId, state);
    }
    
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    } 

    public Page<Order> getAllOrders(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

}
