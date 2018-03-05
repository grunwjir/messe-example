package cz.messe.model.invoice;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import cz.messe.model.order.Order;

@Entity
@Table(name = "invoiced_routes")
public class InvoicedOrder {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    @NotNull
    @Column(name = "price")
    private BigDecimal price;
    
    @NotNull
    @Column(name = "price_discounted")
    private BigDecimal priceDiscounted;
    
    @Column(name = "insurance")
    private BigDecimal insurance;
    
    @NotBlank
    @Column(name = "town_from")
    private String townFrom;
    
    @NotNull
    @Column(name = "town_to")
    private String townTo;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    public InvoicedOrder() {
        
    }

    public InvoicedOrder(Order order) {
        this.order = order;
    }
    
    
    public InvoicedOrder(BigDecimal price, BigDecimal priceDiscounted,
            BigDecimal insurance, String townFrom, String townTo, Order order) {
        this.price = price;
        this.priceDiscounted = priceDiscounted;
        this.insurance = insurance;
        this.townFrom = townFrom;
        this.townTo = townTo;
        this.order = order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPriceDiscounted() {
        return priceDiscounted;
    }

    public void setPriceDiscounted(BigDecimal priceDiscounted) {
        this.priceDiscounted = priceDiscounted;
    }

    public BigDecimal getInsurance() {
        return insurance;
    }

    public void setInsurance(BigDecimal insurance) {
        this.insurance = insurance;
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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
     
}
