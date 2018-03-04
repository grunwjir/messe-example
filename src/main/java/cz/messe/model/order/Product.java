package cz.messe.model.order;

public enum Product {
    STANDART("Standart", "St"), 
    EXPRESS("Express", "Ex"), 
    EXTREME("Extreme", "Xx"); 
    
    private final String name;
    private final String abbreviation;
    
    private Product(String name, String abbreviation) {
        this.name = name;
        this.abbreviation = abbreviation;
    }

    public String getName() {
        return name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }
      
}
