package simple_shops_and_customers;

public class Account {

    private AccountType type;
    private String details;
    private Double balance;
    private Double limit;

    public Account(
        AccountType type,
        String details,
        Double balance,
        Double limit

    ) {
        this.type = type;
        this.details = details;
        this.balance = balance;
        this.limit = limit;
    }

    public Boolean debit( Double debitAmount ){
        if ( authorise( debitAmount ) ){
            this.balance -= debitAmount;
            return true;
        }
        return false;
    }

    public void credit( Double creditAmount ){
        this.balance += creditAmount;
    }

    public Boolean authorise( Double amount ){
        Boolean overLimit = ( ( this.balance - amount ) < this.limit );
        return !overLimit;
    }

    public Double getBalance(){
        return this.balance;
    }

    public AccountType getType(){
        return this.type;
    }

}
