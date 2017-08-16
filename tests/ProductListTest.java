import org.junit.Before;
import org.junit.Test;
import simple_shops_and_customers.*;

import java.util.HashMap;

import static org.junit.Assert.*;

public class ProductListTest {

    private Product taleTwoCities1;
    private Product taleTwoCities2;
    private Product ofotcn;
    private ProductList bookStore;

    @Before
    public void before(){
        taleTwoCities1 = new Product(
                "TaleTwoCities11492",
                10.99,
                12.99,
                "Unabridged copy of A Tale of Two Cities by Charles Dickens"
        );
        taleTwoCities2 = new Product(
                "TaleTwoCities11492",
                10.99,
                12.99,
                "Unabridged copy of A Tale of Two Cities by Charles Dickens"
        );
        ofotcn = new Product(
                "OneFlewOverTheCuckoosNest345672",
                6.00,
                9.00,
                "Unabridged copy of One Flew Over the Cuckoos Nest by Ken Kesey"
        );
        bookStore = new ProductList();
    }

    @Test
    public void testStoreProduct(){
        assertEquals( 0, bookStore.numberProductsInStore() );
        bookStore.storeProduct( taleTwoCities1 );
        assertEquals( 1, bookStore.numberProductsInStore() );
    }

    @Test
    public void testStoreProduct__Null(){
        bookStore.storeProduct( null );
        assertEquals( 0, bookStore.numberProductsInStore() );
    }

    @Test
    public void testProductInStore(){
        bookStore.storeProduct( taleTwoCities1 );
        assertTrue( bookStore.productInStore( taleTwoCities1 ) );
        assertFalse( bookStore.productInStore( taleTwoCities2 ) );
    }

    @Test
    public void testProductInStore__Null(){
        assertFalse( bookStore.productInStore( null ) );
    }

    @Test
    public void testFetchProduct(){
        bookStore.storeProduct( taleTwoCities1 );
        bookStore.storeProduct( taleTwoCities2 );
        Product fetchProduct = bookStore.fetchProduct( taleTwoCities1 );
        assertEquals( fetchProduct, taleTwoCities1 );
        assertEquals( 1, bookStore.numberProductsInStore() );
        assertFalse( bookStore.productInStore( taleTwoCities1 ) );
    }

    @Test
    public void testFetchProduct__Null() {
        bookStore.storeProduct(taleTwoCities1);
        Product fetchProduct = bookStore.fetchProduct( null );
        assertNull( fetchProduct );
        assertEquals( 1, bookStore.numberProductsInStore() );
    }

    @Test
    public void testInspectProduct(){
        bookStore.storeProduct( taleTwoCities1 );
        Product inspectProduct = bookStore.inspectProduct( taleTwoCities1 );
        assertEquals( inspectProduct, taleTwoCities1 );
        assertEquals( 1, bookStore.numberProductsInStore() );
        assertTrue( bookStore.productInStore( taleTwoCities1 ) );
    }

    @Test
    public void testInspectProduct__Null(){
        bookStore.storeProduct( taleTwoCities1 );
        Product inspectProduct = bookStore.inspectProduct( null );
        assertNull( inspectProduct );
        assertEquals( 1, bookStore.numberProductsInStore() );
    }

    @Test
    public void testGetStockReport(){
        bookStore.storeProduct( taleTwoCities1 );
        bookStore.storeProduct( taleTwoCities2 );
        bookStore.storeProduct( ofotcn );
        HashMap< String, Integer > results = bookStore.getStockReport();
        Integer expected = 2;
        assertEquals( expected, results.get( taleTwoCities1.getIdentifier() ) );
        expected = 1;
        assertEquals( expected, results.get( ofotcn.getIdentifier() ) );
    }


}
