package simple_shops_and_customers;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductList {

    private ArrayList< Product > store;

    public ProductList(){
        this.store = new ArrayList<>();
    }

    public void storeProduct( Product product ){
        if ( product != null ) {
            this.store.add(product);
        }
    }

    public Product inspectProduct( Product product ){
        if ( product != null ) {
            int index = this.store.indexOf(product);
            if ( index >= 0 ) {
                return this.store.get(index);
            }
        }
        return null;
    }

    public Product fetchProduct( Product product ){
        if ( product != null ) {
            int index = this.store.indexOf(product);
            if ( index >= 0 ) {
                return this.store.remove( index );
            }
        }
        return null;
    }

    public Boolean productInStore( Product product ){
        return this.store.contains( product );
    }

    public int numberProductsInStore(){
        return this.store.size();
    }

    public HashMap< String, Integer > getStockReport(){

        HashMap< String, Integer > resultMap = new HashMap<>();

        for ( Product product : this.store ) {
            String productID = product.getIdentifier();

            if ( resultMap.get( productID ) == null ){
                resultMap.put( productID, 1 );
            } else {
                resultMap.put( productID, resultMap.get( productID ) + 1 );
            }

        }

        return resultMap;

    }

}
