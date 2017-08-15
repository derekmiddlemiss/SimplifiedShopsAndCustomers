import org.junit.Before;
import org.junit.Test;
import simple_shops_and_customers.*;

import static org.junit.Assert.*;

public class AccountTest {

    private Account creditCardLowLimit;

    @Before
    public void before(){
        creditCardLowLimit = new Account(
                AccountType.CREDITCARD,
                "Frank Mitty, Number 3434676784841212",
                0.0,
                -100.0,
                "1234"
                );
    }

    @Test
    public void testGetBalance__CorrectPin(){
        assertEquals( 0.0, creditCardLowLimit.getBalance( "1234" ), 0.001 );
    }

    @Test
    public void testGetBalance__WrongPin(){
        assertNull( creditCardLowLimit.getBalance( "2134" ) );
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
        creditCardLowLimit.debit( 30.0, "1234" );
        assertEquals( -30.0, creditCardLowLimit.getBalance( "1234" ), 0.001 );
    }

    @Test
    public void testDebit__NotAllowed(){
        creditCardLowLimit.debit( 200.0, "1234" );
        assertEquals( 0.0, creditCardLowLimit.getBalance( "1234" ), 0.001 );
    }

    @Test
    public void testDebit__WrongPin(){
        creditCardLowLimit.debit( 30.0, "2134" );
        assertEquals( 0.0, creditCardLowLimit.getBalance( "1234" ), 0.001 );
    }

    @Test
    public void testBooleanResponseDebit__Allowed(){
        Boolean response = creditCardLowLimit.debit( 30.0, "1234" );
        assertTrue( response );
    }

    @Test
    public void testBooleanResponseDebit__NotAllowed(){
        Boolean response = creditCardLowLimit.debit( 200.0, "1234" );
        assertFalse( response );
    }

    @Test
    public void testBooleanResponseDebit__WrongPin(){
        Boolean response = creditCardLowLimit.debit( 30.0, "2134" );
        assertFalse( response );
    }

    @Test
    public void testCredit(){
        creditCardLowLimit.debit( 30.0, "1234" );
        creditCardLowLimit.credit( 50.0 );
        assertEquals( 20.0, creditCardLowLimit.getBalance( "1234" ), 0.001 );
    }

    @Test
    public void testGetType(){
        assertEquals( AccountType.CREDITCARD, creditCardLowLimit.getType() );
    }

}
