package simple_shops_and_customers;

public class Shop {

    private int shopID;
    private String name;
    private ProductList products;
    private Double current;
    private Double sales;
    private Double refunds;

    public Shop(
            int shopID,
            String name,
            Double current,
            Double sales,
            Double refunds
    ) {
        this.shopID = shopID;
        this.name = name;
        this.products = new ProductList();
        this.current = current;
        this.sales = sales;
        this.refunds = refunds;
    }

    public int getID(){
        return this.shopID;
    }

    public String getName(){
        return this.name;
    }

    public Double getCurrent(){
        return this.current;
    }

    public Double getSales(){
        return this.sales;
    }

    public Double getRefunds(){
        return this.refunds;
    }

    public void receiveProduct( Product product ){
        this.products.storeProduct( product );
        this.refunds -= product.getRetailPrice();
    }

    public Product provideProduct( Product product ){
        Product fetchProduct = this.products.fetchProduct( product );
        this.sales += product.getRetailPrice();
        return fetchProduct;
    }

    public void restockProduct( Product product ){
        this.products.storeProduct( product );
        this.current -= product.getWholesalePrice();
    }

    public Boolean productInStore( Product product ){
        return this.products.productInStore( product );
    }

    public Double getTotal(){
        return this.current + this.sales + this.refunds;
    }

}
