package cz.messe.model.invoice;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import cz.messe.model.customer.Customer;

@Entity
@Table(name = "invoice")
public class Invoice {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Column(name = "period_name")
    private String periodName;
    
    @NotNull
    @Column(name = "isue_date")
    private LocalDate issueDate;
    
    @NotNull
    @Column(name = "due_date")
    private LocalDate dueDate;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    
    @JsonIgnore
    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InvoicedOrder> routes = new ArrayList<>(); 
    
    @NotNull
    @Column(name ="tax_amount")
    private BigDecimal taxAmount;
    
    @NotNull
    @Column(name ="amount")
    private BigDecimal amount;
    
    @NotNull
    @Column(name ="amount_with_tax")
    private BigDecimal amountWithTax;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            name="invoice_calculated_items",
            joinColumns = @JoinColumn( name="invoice_id"),
            inverseJoinColumns = @JoinColumn( name="item_id")
    )
    private List<InvoiceItem> calculatedItems = new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            name="invoice_user_items",
            joinColumns = @JoinColumn( name="invoice_id"),
            inverseJoinColumns = @JoinColumn( name="item_id")
    )
    private List<InvoiceItem> userItems = new ArrayList<>();
     
    public Invoice() {
        
    }
    
    public Invoice(String periodName, LocalDate issueDate, LocalDate dueDate, Customer customer, BigDecimal taxAmount,
            BigDecimal amount, BigDecimal amountWithTax) {
        this.periodName = periodName;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.customer = customer;
        this.taxAmount = taxAmount;
        this.amount = amount;
        this.amountWithTax = amountWithTax;
    }

    public void addCalculatedItem(InvoiceItem item) {
        calculatedItems.add(item);
    }
    
    public void removecalculatedItem(InvoiceItem item) {
        calculatedItems.remove(item);
    }
    
    public void addUserItem(InvoiceItem item) {
        userItems.add(item);
    }
    
    public void removeUserItem(InvoiceItem item) {
        userItems.remove(item);
    }
    
    public void addRoute(InvoicedOrder route) {
        routes.add(route);
        route.setInvoice(this);
    }
    
    public void removeRoute(InvoicedOrder route) {
        routes.remove(route);
        route.setInvoice(null);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPeriodName() {
        return periodName;
    }

    public void setPeriodName(String periodName) {
        this.periodName = periodName;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public List<InvoiceItem> getCalculatedItems() {
        return calculatedItems;
    }

    public void setCalculatedItems(List<InvoiceItem> calculatedItems) {
        this.calculatedItems = calculatedItems;
    }

    public List<InvoiceItem> getUserItems() {
        return userItems;
    }

    public void setUserItems(List<InvoiceItem> userItems) {
        this.userItems = userItems;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<InvoicedOrder> getRoutes() {
        return routes;
    }

    public void setRoutes(List<InvoicedOrder> routes) {
        this.routes = routes;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmountWithTax() {
        return amountWithTax;
    }

    public void setAmountWithTax(BigDecimal amountWithTax) {
        this.amountWithTax = amountWithTax;
    }
    
}
