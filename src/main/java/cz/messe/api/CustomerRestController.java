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

import cz.messe.model.customer.Customer;
import cz.messe.service.customer.CustomerService;

@RestController
@RequestMapping("customers")
public class CustomerRestController {
     
    @Autowired
    private CustomerService customerService;
    
    @GetMapping("/{id}")
    public Customer getCustomer(@PathVariable("id") Long id) {
        Optional<Customer> customer = customerService.getCustomer(id);
        
        if (!customer.isPresent()) {
            throw new ResourceNotFoundException();
        }
            
        return customer.get();
    }
    
    @PostMapping
    public ResponseEntity<Void> createCustomer(@Valid @RequestBody Customer customer) {
        Long customerId = customerService.createCustomer(customer);
        
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(customerId).toUri();
        
        return ResponseEntity.created(location).build();
    }
    
    @PutMapping("/{id}")
    public void updateCustomer(@PathVariable("id") Long id, @Valid @RequestBody Customer customer) {
        Optional<Customer> updatingCustomer = customerService.getCustomer(id);
              
        if (!updatingCustomer.isPresent()) {
            throw new ResourceNotFoundException();
        }
        
        customer.setId(id);
        customerService.updateCustomer(customer);
    }
    
    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable("id") Long id) {
        Optional<Customer> customer = customerService.getCustomer(id);
              
        if (!customer.isPresent()) {
            throw new ResourceNotFoundException();
        }
        
        customerService.deleteCustomer(id);
   
    } 

    @GetMapping
    public Page<Customer> getAllCustomers(
            @RequestParam(value = "page") Integer page, 
            @RequestParam(value = "size") Integer size) {
        return customerService.getAllCustomers(PageRequest.of(page, size));
    }
    
}