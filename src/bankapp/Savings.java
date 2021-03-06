package bankapp;

public class Savings extends Account{
    private static String accountType = "Savings";
    
    Savings(int accountId, double initialDeposit){
        super(accountId);
        this.setBalance(initialDeposit);       
    }
       
    @Override
    public AccountType getAccountType() {
       return AccountType.Savings;
    }
}
