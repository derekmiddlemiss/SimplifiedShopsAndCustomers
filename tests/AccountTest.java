import org.junit.Before;
import org.junit.Test;
import simple_shops_and_customers.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AccountTest {

    private Account creditCardLowLimit;

    @Before
    public void before(){
        creditCardLowLimit = new Account(
                AccountType.CREDITCARD,
                "Frank Mitty, Number 3434676784841212",
                0.0,
                -100.0
                );
    }

    @Test
    public void testAuthorise__Allowed(){
        assertTrue( creditCardLowLimit.authorise( 30.0 ) );
    }

    @Test
    public void testAuthorise__NotAllowed(){
        assertFalse( creditCardLowLimit.authorise( 200.0) );
    }

    @Test
    public void testDebit__Allowed(){
        creditCardLowLimit.debit( 30.0 );
        assertEquals( -30.0, creditCardLowLimit.getBalance(), 0.001 );
    }

    @Test
    public void testDebit__NotAllowed(){
        creditCardLowLimit.debit( 200.0 );
        assertEquals( 0.0, creditCardLowLimit.getBalance(), 0.001 );
    }

    @Test
    public void testCredit(){
        creditCardLowLimit.debit( 30.0 );
        creditCardLowLimit.credit( 50.0 );
        assertEquals( 20.0, creditCardLowLimit.getBalance(), 0.001 );
    }

    @Test
    public void testGetType(){
        assertEquals( AccountType.CREDITCARD, creditCardLowLimit.getType() );
    }

}
