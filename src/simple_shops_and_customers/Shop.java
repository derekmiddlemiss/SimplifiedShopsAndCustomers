package simple_shops_and_customers;

import java.util.ArrayList;
import java.util.HashMap;

public class Shop {

    private int shopID;
    private int transactionIDCounter;
    private String name;
    private ProductList products;
    private TransactionMap transactions;
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
        this.transactionIDCounter = 1;
        this.name = name;
        this.products = new ProductList();
        this.transactions = new TransactionMap();
        this.current = current;
        this.sales = sales;
        this.refunds = refunds;
    }

    public void sellProduct( Product product, Customer customer, AccountType customerAccount, String pin ){
        Transaction sellProduct = new Transaction(
                transactionIDCounter,
                customer,
                customerAccount,
                pin,
                this,
                TransactionType.SALE,
                product
        );
        this.transactions.storeTransaction( sellProduct );
        transactionIDCounter++;
    }

    public void refundProduct( Product product, Customer customer, AccountType customerAccount, String pin ){
        Transaction refundProduct = new Transaction(
                transactionIDCounter,
                customer,
                customerAccount,
                pin,
                this,
                TransactionType.REFUND,
                product
        );
        this.transactions.storeTransaction( refundProduct );
        transactionIDCounter++;
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

    public int getNumberOfTransactions() {
        return this.transactions.numberOfTransactions();
    }

    public void receiveProduct( Product product ){
        if ( product != null ) {
            this.products.storeProduct(product);
            this.refunds -= product.getRetailPrice();
        }
    }

    public Product provideProduct( Product product ){
        if ( product != null ) {
            Product fetchProduct = this.products.fetchProduct(product);
            if ( fetchProduct != null ) {
                this.sales += fetchProduct.getRetailPrice();
            }
            return fetchProduct;
        }
        return null;
    }

    public void restockProduct( Product product ){
        if ( product != null ) {
            this.products.storeProduct(product);
            this.current -= product.getWholesalePrice();
        }
    }

    public Boolean productInStore( Product product ){
        return this.products.productInStore( product );
    }

    public Double getTotal(){
        return this.current + this.sales + this.refunds;
    }

    public ArrayList< Transaction > findTransactionsByProductID( String productID ){
        return this.transactions.findTransactionsByProductID( productID );
    }

    public ArrayList< Transaction > findTransactionsByCustomerID( int customerID ){
        return this.transactions.findTransactionsByCustomerID( customerID );
    }

    public HashMap< String, Integer > getSalesReportByProductID(){
        return this.transactions.getSalesReportByProductID();
    }

    public HashMap< String, Integer > getRefundReportByProductID(){
        return this.transactions.getRefundReportByProductID();
    }

}
