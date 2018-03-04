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
    InitializingBean initCustomers() {
        return () -> {
            customerRepository.save(new Customer("Mall Group", new Address("Praha", "Libínská"), 0.25));
            customerRepository.save(new Customer("ABC company s.r.o. ", new Address("Brno", "Táborská"), 0.15));
          };
    }
    
    @Bean
    InitializingBean initOrders() {
        return () -> {
            orderRepository.save(new Order("Praha", "Brno", Product.STANDART, customerRepository.getOne(1L),
                    LocalDate.of(2018, 3, 4), LocalDate.of(2018, 3, 5), new BigDecimal(100.5), null));
            orderRepository.save(new Order("Liberec", "Kladno", Product.STANDART, customerRepository.getOne(1L),
                    LocalDate.of(2018, 3, 4), LocalDate.of(2018, 3, 6), new BigDecimal(310.4), new BigDecimal(300)));
            orderRepository.save(new Order("Ostrava", "Praha", Product.EXPRESS, customerRepository.getOne(1L),
                    LocalDate.of(2018, 3, 4), LocalDate.of(2018, 3, 4), new BigDecimal(80.3), null));
            orderRepository.save(new Order("Choceň", "Lanškroun", Product.EXTREME, customerRepository.getOne(2L),
                    LocalDate.of(2018, 3, 10), LocalDate.of(2018, 3, 10), new BigDecimal(1400.5), null));
            orderRepository.save(new Order("Letohrad", "Zlín", Product.EXTREME, customerRepository.getOne(2L),
                    LocalDate.of(2018, 3, 10), LocalDate.of(2018, 3, 16), new BigDecimal(900.5), new BigDecimal(100)));
            orderRepository.save(new Order("Praha", "Kolín", Product.EXTREME, customerRepository.getOne(2L),
                    LocalDate.of(2018, 4, 1), LocalDate.of(2018, 5, 16), new BigDecimal(190.7), new BigDecimal(150)));
          };
    }
    
}
