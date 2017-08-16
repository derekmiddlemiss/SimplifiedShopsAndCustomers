package simple_shops_and_customers;

import java.util.ArrayList;
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

    public ArrayList< Transaction > findTransactionsByCustomerID(int searchCustomerID ){

        ArrayList< Transaction > resultSet = new ArrayList<>();

        for ( Transaction transaction : this.store.values() ){
            if ( transaction.getCustomerID() == searchCustomerID ){
                resultSet.add( transaction );
            }
        }

        return resultSet;
    }

    public ArrayList< Transaction > findTransactionsByProductID( String searchProductID){
        ArrayList< Transaction > resultSet = new ArrayList<>();

        for ( Transaction transaction : this.store.values() ){
            if ( transaction.getProductIdentifier().equals( searchProductID ) ){
                resultSet.add( transaction );
            }
        }

        return resultSet;

    }

    public HashMap< String, Integer > getSalesReportByProductID(){

        HashMap< String, Integer > resultMap = new HashMap<>();

        for ( Transaction transaction : this.store.values() ){
            Boolean isASale = ( transaction.getType() == TransactionType.SALE );
            Boolean completed = transaction.getCompleted();
            String productID = transaction.getProductIdentifier();

            if ( isASale && completed ) {

                if ( resultMap.get( productID ) == null ){
                    resultMap.put( productID, 1 );
                } else {
                    resultMap.put( productID, resultMap.get( productID ) + 1 );
                }

            }
        }

        return resultMap;

    }

    public HashMap< String, Integer > getRefundReportByProductID(){

        HashMap< String, Integer > resultMap = new HashMap<>();

        for ( Transaction transaction : this.store.values() ){
            Boolean isARefund = ( transaction.getType() == TransactionType.REFUND );
            Boolean completed = transaction.getCompleted();
            String productID = transaction.getProductIdentifier();

            if ( isARefund && completed ) {

                if ( resultMap.get( productID ) == null ){
                    resultMap.put( productID, 1 );
                } else {
                    resultMap.put( productID, resultMap.get( productID ) + 1 );
                }

            }
        }

        return resultMap;

    }

}
