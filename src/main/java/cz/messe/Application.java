package cz.messe;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import cz.messe.model.customer.Address;
import cz.messe.model.customer.Customer;
import cz.messe.model.order.Order;
import cz.messe.model.order.OrderState;
import cz.messe.model.order.Product;
import cz.messe.repository.CustomerRepository;
import cz.messe.repository.route.OrderRepository;

@SpringBootApplication
public class Application {
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private OrderRepository orderRepository;
        
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    InitializingBean init() {
        return () -> {
            Customer customer1 = customerRepository.save(new Customer("Mall Group", new Address("Praha", "Libínská"), 0.25));
            Customer customer2 = customerRepository.save(new Customer("ABC company s.r.o. ", new Address("Brno", "Táborská"), 0.15));
            
            LocalDate date = LocalDate.of(2018, 3, 4);
            
            // these two orders were delivered
            Order o1 = orderRepository.save(new Order("Praha", "Brno", Product.STANDART, customer1, date, date, new BigDecimal(100.5), null));
            Order o2 = orderRepository.save(new Order("Liberec", "Kladno", Product.STANDART, customer1, date, date, new BigDecimal(310.4), new BigDecimal(300)));
            
            // set delivered state
            orderRepository.updateOrderState(o1.getId(), OrderState.CLOSED);
            orderRepository.updateOrderState(o2.getId(), OrderState.CLOSED);
            
            orderRepository.save(new Order("Liberec", "Kladno", Product.STANDART, customer1, date, date, new BigDecimal(310.4), new BigDecimal(300)));
            orderRepository.save(new Order("Ostrava", "Praha", Product.EXPRESS, customer1, date, date, new BigDecimal(80.3), null));
            orderRepository.save(new Order("Choceň", "Lanškroun", Product.EXTREME, customer2, date, date, new BigDecimal(1400.5), null));
            orderRepository.save(new Order("Letohrad", "Zlín", Product.EXTREME, customer2, date, date, new BigDecimal(900.5), new BigDecimal(100)));
            orderRepository.save(new Order("Praha", "Kolín", Product.EXTREME, customer2, date, date, new BigDecimal(190.7), new BigDecimal(150)));
        };
    }
        
}
