package cz.messe.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.messe.repository.CustomerRepository;
import cz.messe.model.Customer;

@Transactional
@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    
    public Optional<Customer> getCustomer(Long id) {
        return customerRepository.findById(id);
    }
    
    public Long createCustomer(Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);
        
        return savedCustomer.getId();
    }
    
    public void updateCustomer(Customer customer) {
        Customer updatingCustomer = customerRepository.findById(customer.getId()).get();

        updatingCustomer.setDiscountCoefficient(customer.getDiscountCoefficient());
        updatingCustomer.setAddress(customer.getAddress());
        
        customerRepository.save(updatingCustomer);
    }
    
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    } 

    public Page<Customer> getAllCustomers(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }
}
