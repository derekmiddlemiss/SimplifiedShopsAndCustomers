import org.junit.Before;
import org.junit.Test;
import simple_shops_and_customers.*;

import static org.junit.Assert.*;

public class AccountMapTest {

    AccountMap accounts;
    Account creditCard;
    Account debitCard;

    @Before
    public void before(){
        accounts = new AccountMap();
        creditCard = new Account(
                AccountType.CREDITCARD,
                "Frank Mitty, Number 3434676784841212",
                0.0,
                -100.0,
                "1234"
        );
        debitCard = new Account(
                AccountType.DEBITCARD,
                "Mitt Franky, Number 1234123456785678",
                0.0,
                -200.0,
                "1234"
        );
    }

    @Test
    public void testAddAccountAndHasAccount(){
        accounts.addAccount( creditCard );
        assertTrue( accounts.hasAccountType( AccountType.CREDITCARD ) );
    }

    @Test
    public void testHasAccountType__False(){
        accounts.addAccount( creditCard );
        assertFalse( accounts.hasAccountType( AccountType.DEBITCARD ) );
    }

    @Test
    public void testGetAccount(){
        accounts.addAccount( creditCard );
        assertEquals( creditCard, accounts.getAccount( AccountType.CREDITCARD ) );
    }

    @Test
    public void testGetAccount__NotThere(){
        accounts.addAccount( creditCard );
        assertNull( accounts.getAccount( AccountType.DEBITCARD ) );
    }


}
