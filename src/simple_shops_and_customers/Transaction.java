package simple_shops_and_customers;

public class Transaction {

    private int transactionID;
    private int customerID;
    private AccountType customerAccount;
    private TransactionType type;
    private String productIdentifier;
    private Double amount;
    private Boolean completed;

    public Transaction(
            int transactionID,
            Customer customer,
            AccountType customerAccount,
            String pin,
            Shop shop,
            TransactionType transactionType,
            Product product
    ) {
        this.transactionID = transactionID;
        this.customerID = customer.getID();
        this.customerAccount = customerAccount;
        this.type = transactionType;
        this.productIdentifier = product.getIdentifier();
        this.amount = product.getRetailPrice();
        this.completed = false;

        if ( transactionType == TransactionType.SALE ){

            Boolean productInStore = shop.productInStore( product );
            Boolean authorised = customer.authorise( customerAccount, product.getRetailPrice() );
            if ( productInStore && authorised ) {
                Product providedProduct = shop.provideProduct( product );
                customer.receiveProduct( providedProduct, customerAccount, pin );
                this.completed = true;
            }

        } else {

            Boolean productInHouse = customer.productInStore( product );
            if ( productInHouse ) {
                Product providedProduct = customer.provideProduct( product, customerAccount );
                shop.receiveProduct( providedProduct );
                this.completed = true;
            }


        }

    }

    public int getID(){
        return this.transactionID;
    }

    public int getCustomerID(){
        return this.customerID;
    }

    public AccountType getCustomerAccount(){
        return this.customerAccount;
    }

    private TransactionType getType(){
        return this.type;
    }

    private String getProductIdentifier(){
        return this.productIdentifier;
    }

    private Double getAmount(){
        return this.amount;
    }

    private Boolean getCompleted(){
        return this.completed;
    }
}
