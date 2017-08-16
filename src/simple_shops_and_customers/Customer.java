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

    public void receiveProduct(Product product, AccountType accountType, String pin ) {
        if ( product != null ) {
            this.products.storeProduct(product);
            this.accounts.getAccount(accountType).debit(product.getRetailPrice(),pin);
        }
    }

    public Product provideProduct(Product product, AccountType accountType) {
        if ( product != null ) {
            Product fetchProduct = this.products.fetchProduct(product);
            if ( fetchProduct != null ) {
                this.accounts.getAccount(accountType).credit(fetchProduct.getRetailPrice());
                return fetchProduct;
            }
        }
        return null;
    }

    public Boolean authorise( AccountType accountType, Double amount ){
        return this.accounts.getAccount( accountType ).authorise( amount );
    }

    public Boolean productInStore( Product product ){
        return this.products.productInStore( product );
    }

    public int numberProductsInStore(){
        return this.products.numberProductsInStore();
    }

    public void addAccount( Account account ){
        this.accounts.addAccount( account );
    }

    public Double getBalance( AccountType accountType, String pin ){
        return this.accounts.getAccount( accountType ).getBalance( pin );
    }

    public Boolean hasAccount( AccountType accountType ){
        return this.accounts.hasAccountType( accountType );
    }

}


