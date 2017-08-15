package simple_shops_and_customers;

import java.util.HashMap;

public class AccountMap {

    private HashMap< AccountType, Account > store;

    public AccountMap(){
        this.store = new HashMap<>();
    }

    public void addAccount( Account account ){
        this.store.put( account.getType(), account );
    }

    public Boolean hasAccountType( AccountType accountType ){
        return this.store.containsKey( accountType );
    }

    public Account getAccount( AccountType accountType ){
        return this.store.get( accountType );
    }

}
