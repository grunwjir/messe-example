package cz.messe.repository.route;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cz.messe.model.customer.Customer;
import cz.messe.model.order.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, OrderRepositoryCustom {
    
    Page<Order> findByCustomer(Customer customer, Pageable pageable);
    
}
