import org.junit.Before;
import org.junit.Test;
import simple_shops_and_customers.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TransactionTest {

    private Customer frank;
    private Shop bookShop;
    private Account creditCard;
    private Product lotr;

    @Before
    public void before(){
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
        frank.addAccount( creditCard );
        bookShop.restockProduct( lotr );
    }

    @Test
    public void testSellLotrToFrank(){
        assertFalse( frank.productInStore( lotr ) );
        assertTrue( bookShop.productInStore( lotr ) );
        assertEquals( 992.0, bookShop.getCurrent(), 0.001 );

        int transactionID = 1;
        Customer customer = frank;
        AccountType accountType = AccountType.CREDITCARD;
        String pin = "1234";
        Shop shop = bookShop;
        TransactionType transactionType = TransactionType.SALE;
        Product product = lotr;
        Transaction lotrToFrank = new Transaction(
                transactionID,
                customer,
                accountType,
                pin,
                shop,
                transactionType,
                product
        );

        assertTrue( frank.productInStore( lotr ) );
        assertFalse( bookShop.productInStore( lotr ) );
        assertEquals( 12.00, bookShop.getSales(), 0.001);
        assertEquals( -12.00, frank.getBalance( AccountType.CREDITCARD, "1234" ), 0.001 );
    }

    @Test
    public void returnLotrToShop(){

        int transactionID = 1;
        Customer customer = frank;
        AccountType accountType = AccountType.CREDITCARD;
        String pin = "1234";
        Shop shop = bookShop;
        TransactionType transactionType = TransactionType.SALE;
        Product product = lotr;
        Transaction lotrToFrank = new Transaction(
                transactionID,
                customer,
                accountType,
                pin,
                shop,
                transactionType,
                product
        );

        assertTrue( frank.productInStore( lotr ) );
        assertFalse( bookShop.productInStore( lotr ) );

        transactionID = 2;
        customer = frank;
        accountType = AccountType.CREDITCARD;
        pin = "1234";
        shop = bookShop;
        transactionType = TransactionType.REFUND;
        product = lotr;
        Transaction lotrToShop = new Transaction(
                transactionID,
                customer,
                accountType,
                pin,
                shop,
                transactionType,
                product
        );

        assertFalse( frank.productInStore( lotr ) );
        assertTrue( bookShop.productInStore( lotr ) );
        assertEquals( 12.00, bookShop.getSales(), 0.001 );
        assertEquals( -12.00, bookShop.getRefunds(), 0.001 );
        assertEquals( 0.00, frank.getBalance( AccountType.CREDITCARD, "1234" ), 0.001 );
    }

}
