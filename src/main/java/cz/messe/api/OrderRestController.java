package cz.messe.api;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import cz.messe.model.order.Order;
import cz.messe.service.order.OrderService;

@RestController
@RequestMapping("orders")
public class OrderRestController {
     
    @Autowired
    private OrderService orderService;
    
    @GetMapping("/{id}")
    public Order getOrder(@PathVariable("id") Long id) {
        Optional<Order> order = orderService.getOrder(id);
        
        if (!order.isPresent()) {
            throw new ResourceNotFoundException();
        }
            
        return order.get();
    }
    
    @PostMapping
    public ResponseEntity<Void> createOrder(@Valid @RequestBody Order order) {
        Long orderId = orderService.createOrder(order);
        
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(orderId).toUri();
        
        return ResponseEntity.created(location).build();
    }
    
    @PutMapping("/{id}")
    public void updateOrder(@PathVariable("id") Long id, @Valid @RequestBody Order order) {
        Optional<Order> updatingOrder = orderService.getOrder(id);
              
        if (!updatingOrder.isPresent()) {
            throw new ResourceNotFoundException();
        }
        
        order.setId(id);
        orderService.updateOrder(order);
    }
    
    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable("id") Long id) {
        Optional<Order> order = orderService.getOrder(id);
              
        if (!order.isPresent()) {
            throw new ResourceNotFoundException();
        }
        
        orderService.deleteOrder(id);
   
    } 

    @GetMapping
    public Page<Order> getAllOrders(
            @RequestParam(value = "page") Integer page, 
            @RequestParam(value = "size") Integer size) {
        return orderService.getAllOrders(PageRequest.of(page, size));
    }
    
}