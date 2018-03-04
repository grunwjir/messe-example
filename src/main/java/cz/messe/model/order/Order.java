package cz.messe.model.order;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import cz.messe.model.customer.Customer;

@Entity
@Table(name = "orders")
public class Order {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    
    @NotBlank
    @Column(name = "town_from")
    private String townFrom;
    
    @NotNull
    @Column(name = "pickup_date")
    private LocalDate pickup;
    
    @NotNull
    @Column(name = "delivery_date")
    private LocalDate delivery;
    
    @NotBlank
    @Column(name = "town_to")
    private String townTo;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "product")
    private Product product;
    
    @NotNull
    @Column(name = "price")
    private BigDecimal price;
    
    @Column(name = "insurance")
    private BigDecimal insurance;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "order_state")
    private OrderState state = OrderState.ACCEPTED;
    
    protected Order() {
        
    }
    
    public Order(String townFrom, String townTo, Product product, Customer customer, LocalDate pickup, LocalDate delivery,  BigDecimal price, BigDecimal insurance) {
        this.townFrom = townFrom;
        this.townTo = townTo;
        this.product = product;
        this.customer = customer;
        this.price = price;
        this.insurance = insurance;
        this.pickup = pickup;
        this.delivery = delivery;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getTownFrom() {
        return townFrom;
    }

    public void setTownFrom(String townFrom) {
        this.townFrom = townFrom;
    }

    public String getTownTo() {
        return townTo;
    }

    public void setTownTo(String townTo) {
        this.townTo = townTo;
    }
  
    public LocalDate getPickup() {
        return pickup;
    }

    public void setPickup(LocalDate pickup) {
        this.pickup = pickup;
    }

    public LocalDate getDelivery() {
        return delivery;
    }

    public void setDelivery(LocalDate delivery) {
        this.delivery = delivery;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getInsurance() {
        return insurance;
    }

    public void setInsurance(BigDecimal insurance) {
        this.insurance = insurance;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }
   
}
