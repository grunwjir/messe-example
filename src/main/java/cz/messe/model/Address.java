package cz.messe.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Embeddable
public class Address {
    
    @Column(name = "town")
    @NotBlank
    private String town;
    
    @Column(name = "street")
    @NotBlank
    private String street;
    
    protected Address() {
        
    }
    
    public Address(String town, String street) {
        this.town = town;
        this.street = street;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
    
}
