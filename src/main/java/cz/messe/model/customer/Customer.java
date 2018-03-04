package cz.messe.model.customer;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "customers")
public class Customer {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "company_name")
    @NotBlank
    private String companyName;
    
    @Embedded
    @Valid
    private Address address;
    
    @Column(name = "discount_coefficient")
    private double discountCoefficient = 0;
    
    protected Customer() {  
    }
    
    public Customer(String companyName, Address address) {
       this(companyName, address, 0);
    }
    
    public Customer(String companyName, Address address, double discountCoefficient) {
        this.companyName = companyName;
        this.address = address;
        this.discountCoefficient = discountCoefficient;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public double getDiscountCoefficient() {
        return discountCoefficient;
    }

    public void setDiscountCoefficient(double discountCoefficient) {
        this.discountCoefficient = discountCoefficient;
    }
    
}
