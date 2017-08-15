package simple_shops_and_customers;

import java.util.HashMap;

public class TransactionMap {

    private HashMap< Integer, Transaction > store;

    public TransactionMap(){
        this.store = new HashMap<>();
    }

    public void storeTransaction( Transaction transaction ){
        if ( transaction != null ){
            this.store.put( transaction.getID(), transaction );
        }
    }

    public Transaction inspectTransactionByID( int transactionID ){
        return this.store.get( transactionID );
    }

    public int numberOfTransactions(){
        return this.store.size();
    }
}
