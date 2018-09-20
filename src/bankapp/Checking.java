package bankapp;

public class Checking extends Account{
    private static String accountType = "Checking";
       
    Checking(int accountId, double initialDeposit){        
        super(accountId);        
        this.setBalance(initialDeposit);        
    }        

    @Override
    public AccountType getAccountType() {
        return AccountType.Checking;
    }
}
