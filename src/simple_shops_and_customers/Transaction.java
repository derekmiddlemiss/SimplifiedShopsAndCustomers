package simple_shops_and_customers;

public class Transaction {

    private int transactionID;
    private int customerID;
    private AccountType customerAccount;
    private int shopID;
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
        this.shopID = shop.getID();
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

}
