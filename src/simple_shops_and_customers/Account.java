package simple_shops_and_customers;

public class Account {

    private AccountType type;
    private String details;
    private Double balance;
    private Double limit;
    private int pinHash;

    public Account(
        AccountType type,
        String details,
        Double balance,
        Double limit,
        String setupPin
    ) {
        this.type = type;
        this.details = details;
        this.balance = balance;
        this.limit = limit;
        this.pinHash = getHashCode( setupPin );
    }

    public Boolean debit( Double debitAmount, String pin ){
        if ( authorise( debitAmount ) && checkPin( pin ) ){
            this.balance -= debitAmount;
            return true;
        }
        return false;
    }

    private int getHashCode( String input ){
        return input.hashCode();
    }

    private Boolean checkPin( String pin ){
        return ( getHashCode( pin ) == this.pinHash );
    }

    public void credit( Double creditAmount ){
        this.balance += creditAmount;
    }

    public Boolean authorise( Double amount ){
        Boolean overLimit = ( ( this.balance - amount ) < this.limit );
        return !overLimit;
    }

    public Double getBalance( String pin ){
        if ( checkPin( pin ) ) {
            return this.balance;
        }
        return null;
    }

    public AccountType getType(){
        return this.type;
    }

}
