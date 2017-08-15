package simple_shops_and_customers;

public class Product {

    private String identifier;
    private Double wholesalePrice;
    private Double retailPrice;
    private String description;

    public Product(
            String identifier,
            Double wholesalePrice,
            Double retailPrice,
            String description
    ){
        this.identifier = identifier;
        this.wholesalePrice = wholesalePrice;
        this.retailPrice = retailPrice;
        this.description = description;
    }

    public String getIdentifier(){
        return this.identifier;
    }

    public Double getWholesalePrice(){
        return this.wholesalePrice;
    }

    public Double getRetailPrice(){
        return this.retailPrice;
    }

    public String getDescription(){
        return this.description;
    }

}
