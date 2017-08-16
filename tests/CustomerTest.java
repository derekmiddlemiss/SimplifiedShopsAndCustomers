import org.junit.Test;
import simple_shops_and_customers.*;
import org.junit.Before;

import static org.junit.Assert.*;

public class CustomerTest {

    private Customer frank;
    private Account creditCard;
    private Product lotr;
    private Product ofotcn;

    @Before
    public void before() {
        frank = new Customer(
                1,
                "Frank"
        );
        creditCard = new Account(
                AccountType.CREDITCARD,
                "Frank Mitty 1122334455667788",
                0.0,
                -1000.0,
                "1234"
        );
        lotr = new Product(
                "LordOfTheRings113344",
                8.00,
                12.00,
                "Unabridged copy of the Lord of the Rings by J.R.R. Tolkien"
        );
        ofotcn = new Product(
                "OneFlewOverTheCuckoosNest345672",
                6.00,
                9.00,
                "Unabridged copy of One Flew Over the Cuckoos Nest by Ken Kesey"
        );
    }

    @Test
    public void testGetName(){
        assertEquals( "Frank", frank.getName() );
    }

    @Test
    public void testGetID(){
        assertEquals( 1, frank.getID() );
    }

    @Test
    public void testHasAccount__False(){
        assertFalse( frank.hasAccount( AccountType.CREDITCARD ) );
    }

    @Test
    public void testAddAccount(){
        frank.addAccount( creditCard );
        assertTrue( frank.hasAccount( AccountType.CREDITCARD ) );
    }

    @Test
    public void testProductInStore__True(){
        frank.addAccount( creditCard );
        frank.receiveProduct( lotr, AccountType.CREDITCARD, "1234" );
        assertTrue( frank.productInStore( lotr ) );
    }

    @Test
    public void testProductInStore__False(){
        frank.addAccount( creditCard );
        frank.receiveProduct( lotr, AccountType.CREDITCARD, "1234" );
        assertFalse( frank.productInStore( ofotcn ) );
    }

    @Test
    public void testNumberProductsInStore(){
        frank.addAccount( creditCard );
        frank.receiveProduct( lotr, AccountType.CREDITCARD, "1234" );
        frank.receiveProduct( ofotcn, AccountType.CREDITCARD, "1234" );
        assertEquals( 2, frank.numberProductsInStore() );
    }

    @Test
    public void testReceiveProduct(){
        frank.addAccount( creditCard );
        frank.receiveProduct( lotr, AccountType.CREDITCARD, "1234" );
        assertTrue( frank.productInStore( lotr ) );
        assertEquals( -12.00, frank.getBalance( AccountType.CREDITCARD, "1234" ), 0.001 );
    }

    @Test
    public void testReceiveProduct__Null(){
        frank.addAccount( creditCard );
        frank.receiveProduct( null, AccountType.CREDITCARD, "1234" );
        assertEquals( 0, frank.numberProductsInStore() );
    }

    @Test
    public void testProvideProduct(){
        frank.addAccount( creditCard );
        frank.receiveProduct( lotr, AccountType.CREDITCARD, "1234" );
        Product providedProduct = frank.provideProduct( lotr, AccountType.CREDITCARD );
        assertFalse( frank.productInStore( lotr ) );
        assertEquals( providedProduct, lotr );
        assertEquals( 0.00, frank.getBalance( AccountType.CREDITCARD, "1234" ), 0.001 );
    }

    @Test
    public void testProvideProduct__Null(){
        frank.addAccount( creditCard );
        frank.receiveProduct( lotr, AccountType.CREDITCARD, "1234" );
        Product providedProduct = frank.provideProduct( null, AccountType.CREDITCARD );
        assertNull( providedProduct );
    }

    @Test
    public void testProvideProduct__NotInStore(){
        frank.addAccount( creditCard );
        frank.receiveProduct( lotr, AccountType.CREDITCARD, "1234" );
        Product providedProduct = frank.provideProduct( ofotcn, AccountType.CREDITCARD );
        assertNull( providedProduct );
    }

    @Test
    public void testAuthorise__True(){
        frank.addAccount( creditCard );
        assertTrue( frank.authorise( AccountType.CREDITCARD, 600.0) );
    }

    @Test
    public void testAuthorise__False(){
        frank.addAccount( creditCard );
        assertFalse( frank.authorise( AccountType.CREDITCARD, 2000.0 ) );
    }

}
