import org.junit.Before;
import org.junit.Test;
import simple_shops_and_customers.*;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

public class ShopTest {

    private Customer frank;
    private Customer alice;
    private Shop bookShop;
    private Account creditCard;
    private Account debitCard;
    private Product lotr;
    private Product ofotcn;

    @Before
    public void before(){
        frank = new Customer(
                1,
                "Frank"
        );
        alice = new Customer(
                2,
                "Alice"
        );
        creditCard = new Account(
                AccountType.CREDITCARD,
                "Frank Mitty 1122334455667788",
                0.0,
                -1000.0,
                "1234"
        );
        debitCard = new Account(
                AccountType.DEBITCARD,
                "Alice Jones 4455667711223344",
                0.0,
                -1000.0,
                "1234"
        );
        bookShop = new Shop(
                1,
                "Black Books",
                1000.0,
                0.0,
                0.0
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
        frank.addAccount( creditCard );
        alice.addAccount( debitCard );
        bookShop.restockProduct( lotr );
    }

    @Test
    public void testGetID(){
        assertEquals( 1, bookShop.getID() );
    }

    @Test
    public void testGetName(){
        assertEquals( "Black Books", bookShop.getName() );
    }

    @Test
    public void testGetCurrent(){
        assertEquals( 992.0, bookShop.getCurrent(), 0.001 );
    }

    @Test
    public void testGetSales(){
        assertEquals( 0.0, bookShop.getSales(), 0.001 );
    }

    @Test
    public void testGetRefunds(){
        assertEquals( 0.0, bookShop.getRefunds(), 0.001 );
    }

    @Test
    public void testProductInStore(){
        assertTrue( bookShop.productInStore( lotr ) );
    }

    @Test
    public void testReceiveProduct(){
        bookShop.receiveProduct( ofotcn );
        assertTrue( bookShop.productInStore( ofotcn ) );
        assertEquals( -9.00, bookShop.getRefunds(), 0.001 );
    }

    @Test
    public void testReceiveProduct__Null(){
        bookShop.receiveProduct( null );
        assertEquals( 992.0, bookShop.getCurrent(), 0.001);
        assertEquals( 0.0, bookShop.getSales(), 0.001);
        assertEquals( 0.0, bookShop.getRefunds(), 0.001 );
    }

    @Test
    public void testProvideProduct(){
        Product providedProduct = bookShop.provideProduct( lotr );
        assertEquals( providedProduct, lotr );
        assertFalse( bookShop.productInStore( lotr ) );
        assertEquals( 12.00, bookShop.getSales(), 0.001 );
    }

    @Test
    public void testProvideProduct__Null(){
        Product providedProduct = bookShop.provideProduct( null );
        assertNull( providedProduct );
        assertEquals( 992.0, bookShop.getCurrent(), 0.001);
        assertEquals( 0.0, bookShop.getSales(), 0.001);
        assertEquals( 0.0, bookShop.getRefunds(), 0.001 );
    }

    @Test
    public void testProvideProduct__NotInStore(){
        Product providedProduct = bookShop.provideProduct( ofotcn );
        assertNull( providedProduct );
        assertEquals( 992.0, bookShop.getCurrent(), 0.001);
        assertEquals( 0.0, bookShop.getSales(), 0.001);
        assertEquals( 0.0, bookShop.getRefunds(), 0.001 );
    }

    @Test
    public void testGetTotal(){
        bookShop.provideProduct( lotr );
        assertEquals( 1004.0, bookShop.getTotal(), 0.001);
    }

    @Test
    public void testSellLotRToFrank(){
        assertFalse( frank.productInStore( lotr ) );
        assertTrue( bookShop.productInStore( lotr ) );
        assertEquals( 992.0, bookShop.getCurrent(), 0.001 );
        assertEquals( 0, bookShop.getNumberOfTransactions() );

        bookShop.sellProduct( lotr, frank, AccountType.CREDITCARD, "1234" );

        assertTrue( frank.productInStore( lotr ) );
        assertFalse( bookShop.productInStore( lotr ) );
        assertEquals( 12.00, bookShop.getSales(), 0.001);
        assertEquals( -12.00, frank.getBalance( AccountType.CREDITCARD, "1234" ), 0.001 );
        assertEquals( 1, bookShop.getNumberOfTransactions() );
    }

    @Test
    public void testSellOFOtCNToFrank__NotInStore(){
        assertFalse( bookShop.productInStore( ofotcn ) );
        bookShop.sellProduct( ofotcn, frank, AccountType.CREDITCARD, "1234");
        assertFalse( bookShop.productInStore( ofotcn ) );
        assertFalse( frank.productInStore( ofotcn ) );
        assertEquals( 0.0, bookShop.getSales(), 0.001);
        assertEquals( 0.0, frank.getBalance( AccountType.CREDITCARD, "1234"), 0.001 );
        assertEquals( 1, bookShop.getNumberOfTransactions() );
    }

    @Test
    public void testFrankSellAndReturnLotR(){
        bookShop.sellProduct( lotr, frank, AccountType.CREDITCARD, "1234" );
        assertTrue( frank.productInStore( lotr ) );
        assertFalse( bookShop.productInStore( lotr ) );

        bookShop.refundProduct( lotr, frank, AccountType.CREDITCARD, "1234" );
        assertFalse( frank.productInStore( lotr ) );
        assertTrue( bookShop.productInStore( lotr ) );
        assertEquals( 2, bookShop.getNumberOfTransactions() );
        assertEquals( 12.00, bookShop.getSales(), 0.001);
        assertEquals( -12.00, bookShop.getRefunds(), 0.001);
        assertEquals( 0.00, frank.getBalance( AccountType.CREDITCARD, "1234" ), 0.001);
    }

    @Test
    public void testGetTransactionsByProductID(){
        bookShop.restockProduct( ofotcn );
        bookShop.sellProduct( lotr, frank, AccountType.CREDITCARD, "1234" );
        bookShop.sellProduct( ofotcn, frank, AccountType.CREDITCARD, "1234" );
        bookShop.refundProduct( lotr, frank, AccountType.CREDITCARD, "1234" );
        ArrayList< Transaction > transactions = bookShop.findTransactionsByProductID( lotr.getIdentifier() );
        assertEquals( 2, transactions.size() );
    }

    @Test
    public void testGetTransactionsByCustomerID(){
        bookShop.restockProduct( ofotcn );
        bookShop.sellProduct( lotr, frank, AccountType.CREDITCARD, "1234" );
        bookShop.sellProduct( ofotcn, alice, AccountType.DEBITCARD, "1234" );
        bookShop.refundProduct( lotr, frank, AccountType.CREDITCARD, "1234" );
        ArrayList< Transaction > transactions = bookShop.findTransactionsByCustomerID( alice.getID() );
        assertEquals( 1, transactions.size() );
    }

    @Test
    public void testGetSalesReportByProductID(){
        bookShop.restockProduct( ofotcn );
        bookShop.sellProduct( lotr, frank, AccountType.CREDITCARD, "1234" );
        bookShop.sellProduct( ofotcn, alice, AccountType.DEBITCARD, "1234" );
        bookShop.refundProduct( lotr, frank, AccountType.CREDITCARD, "1234" );
        HashMap< String, Integer > report = bookShop.getSalesReportByProductID();
        assertEquals( 2, report.size() );
        Integer expected = 1;
        assertEquals( expected, report.get( lotr.getIdentifier() ) );
    }

    @Test
    public void testGetRefundReportByProductID(){
        bookShop.restockProduct( ofotcn );
        bookShop.sellProduct( lotr, frank, AccountType.CREDITCARD, "1234" );
        bookShop.sellProduct( ofotcn, alice, AccountType.DEBITCARD, "1234" );
        bookShop.refundProduct( lotr, frank, AccountType.CREDITCARD, "1234" );
        HashMap< String, Integer > report = bookShop.getRefundReportByProductID();
        assertEquals( 1, report.size() );
        Integer expected = 1;
        assertEquals( expected, report.get( lotr.getIdentifier() ) );
    }




}
