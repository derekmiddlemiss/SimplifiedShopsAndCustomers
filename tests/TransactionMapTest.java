import org.junit.Test;
import simple_shops_and_customers.*;
import org.junit.Before;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class TransactionMapTest {

    private Customer frank;
    private Customer alice;
    private Account creditCardFrank;
    private Account debitCardAlice;
    private Shop bookShop;
    private Product lotr1;
    private Product lotr2;
    private Product ofotcn;
    private Transaction sellLotr1Frank;
    private Transaction sellLotr2Alice;
    private Transaction refundOFOtCNFrank;
    private TransactionMap transactions;


    @Before
    public void before() {
        frank = new Customer(
                1,
                "Frank"
        );

        alice = new Customer(
                2,
                "Alice"
        );

        creditCardFrank = new Account(
                AccountType.CREDITCARD,
                "Frank Mitty 1122334455667788",
                0.0,
                -1000.0,
                "1234"
        );

        debitCardAlice = new Account(
                AccountType.DEBITCARD,
                "Alice Jones 3344112255664433",
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

        lotr1 = new Product(
                "LordOfTheRings113344",
                8.00,
                12.00,
                "Unabridged copy of the Lord of the Rings by J.R.R. Tolkien"
        );

        lotr2 = new Product(
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

        frank.addAccount(creditCardFrank);
        alice.addAccount(debitCardAlice);
        bookShop.restockProduct(lotr1);
        bookShop.restockProduct(lotr2);
        bookShop.restockProduct(ofotcn);
        bookShop.sellProduct(ofotcn, frank, AccountType.CREDITCARD, "1234");

        sellLotr1Frank = new Transaction(
                1,
                frank,
                AccountType.CREDITCARD,
                "1234",
                bookShop,
                TransactionType.SALE,
                lotr1
        );

        sellLotr2Alice = new Transaction(
                2,
                alice,
                AccountType.DEBITCARD,
                "1234",
                bookShop,
                TransactionType.SALE,
                lotr2
        );

        refundOFOtCNFrank = new Transaction(
                3,
                frank,
                AccountType.CREDITCARD,
                "1234",
                bookShop,
                TransactionType.REFUND,
                ofotcn
        );

        transactions = new TransactionMap();

    }

    @Test
    public void testNumberOfTransactions(){
        assertEquals( 0, transactions.numberOfTransactions() );
    }

    @Test
    public void testStoreTransaction(){
        transactions.storeTransaction( sellLotr1Frank );
        assertEquals( 1, transactions.numberOfTransactions() );
    }

    @Test
    public void testInspectTransactionByID(){
        transactions.storeTransaction( sellLotr1Frank );
        assertEquals( sellLotr1Frank, transactions.inspectTransactionByID( sellLotr1Frank.getID() ) );
    }

    @Test
    public void testFindTransactionsByCustomerID(){
        transactions.storeTransaction( sellLotr1Frank );
        transactions.storeTransaction( sellLotr2Alice );
        transactions.storeTransaction( refundOFOtCNFrank );
        ArrayList< Transaction > results = transactions.findTransactionsByCustomerID( frank.getID() );
        assertEquals( 3, transactions.numberOfTransactions() );
        assertEquals( 2, results.size() );
    }

    @Test
    public void testFindTransactionsByProductID(){
        transactions.storeTransaction( sellLotr1Frank );
        transactions.storeTransaction( sellLotr2Alice );
        transactions.storeTransaction( refundOFOtCNFrank );
        ArrayList< Transaction > results = transactions.findTransactionsByProductID( lotr1.getIdentifier() );
        assertEquals( 3, transactions.numberOfTransactions() );
        assertEquals( 2, results.size() );
    }

    @Test
    public void testGetSalesReportByProductID(){
        transactions.storeTransaction( sellLotr1Frank );
        transactions.storeTransaction( sellLotr2Alice );
        transactions.storeTransaction( refundOFOtCNFrank );
        HashMap< String, Integer > results = transactions.getSalesReportByProductID();
        Integer expected = 2;
        assertEquals( expected, results.get( lotr1.getIdentifier() ) );
        assertEquals( 1, results.size() );
    }

    @Test
    public void testGetRefundsReportByProductID(){
        transactions.storeTransaction( sellLotr1Frank );
        transactions.storeTransaction( sellLotr2Alice );
        transactions.storeTransaction( refundOFOtCNFrank );
        HashMap< String, Integer > results = transactions.getRefundReportByProductID();
        Integer expected = 1;
        assertEquals( expected, results.get( ofotcn.getIdentifier() ) );
        assertEquals( 1, results.size() );
    }

}
