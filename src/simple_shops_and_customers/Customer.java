package simple_shops_and_customers;

public class Customer {

    private int customerID;
    private String name;
    private ProductList products;
    private AccountMap accounts;

    public Customer(
            int customerID,
            String name
    ) {
        this.customerID = customerID;
        this.name = name;
        this.products = new ProductList();
        this.accounts = new AccountMap();
    }

    public int getID() {
        return this.customerID;
    }

    public String getName() {
        return this.name;
    }

    public void receiveProduct(Product product, AccountType accountType) {
        this.products.storeProduct(product);
        this.accounts.getAccount(accountType).debit(product.getRetailPrice());
    }

    public Product provideProduct(Product product, AccountType accountType) {
        Product fetchProduct = this.products.fetchProduct(product);
        this.accounts.getAccount( accountType ).credit(product.getRetailPrice());
        return fetchProduct;
    }

    public Boolean authorise( AccountType accountType, Double amount ){
        return this.accounts.getAccount( accountType ).authorise( amount );
    }

    public Boolean productInStore( Product product ){
        return this.products.productInStore( product );
    }

    public void addAccount( Account account ){
        this.accounts.addAccount( account );
    }

    public Double getBalance( AccountType accountType ){
        return this.accounts.getAccount( accountType ).getBalance();
    }

}


