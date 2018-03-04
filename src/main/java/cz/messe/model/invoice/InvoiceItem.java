package cz.messe.model.invoice;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name ="invoice_items")
public class InvoiceItem {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Column(name = "name")
    private String name;
            
    @NotNull
    @Column(name ="tax_amount")
    private BigDecimal taxAmount;
    
    @NotNull
    @Column(name ="amount")
    private BigDecimal amount;
    
    @NotNull
    @Column(name ="amount_with_tax")
    private BigDecimal amountWithTax;
    
    @NotNull
    @Column(name ="tax_coefficient")
    private double taxCoefficient;

    public InvoiceItem() {
        
    }
    
    public InvoiceItem(String name, BigDecimal taxAmount,
            BigDecimal amount, BigDecimal amountWithTax, double taxCoefficient) {
        this.name = name;
        this.taxAmount = taxAmount;
        this.amount = amount;
        this.amountWithTax = amountWithTax;
        this.taxCoefficient = taxCoefficient;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        
        if (!(obj instanceof InvoiceItem)) {
            return false;
        }
        
        return Objects.equals(id, ((InvoiceItem) obj).id);
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public double getTaxCoefficient() {
        return taxCoefficient;
    }

    public void setTaxCoefficient(double taxCoefficient) {
        this.taxCoefficient = taxCoefficient;
    }
    
}
