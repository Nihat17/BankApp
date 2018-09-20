/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankapp;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Bank implements Serializable{
    private DBService database = new DBService();  
    
    int openAccount(String firstName, String lastName, String ssn, AccountType accountType, double balance){
        return database.AddAccount(firstName, lastName, ssn, accountType, balance);
    }       
    
    Customer getCustomer(int accountId){
        return database.getAccount(accountId);
    }
    
    ArrayList<Customer> getCustomers() throws SQLException{
        return database.getAllAccounts();
    }
   
    boolean closeAccount(int accountId) {
        return database.deleteAccount(accountId);
    }

    
     public void withdraw(int accountId,double amount) throws InsufficientFundsException{
        Customer customer = getCustomer(accountId);
        double transactionFee = getTransactionFee(customer.getAccount().getAccountType());
        if(amount + transactionFee > customer.getAccount().getBalance()){
            throw new InsufficientFundsException();
        }
        double newBalance = customer.getAccount().getBalance() - (amount + transactionFee);
        database.updateAccount(accountId, newBalance);               
    }
    
    public void deposit(int accountId, double amount) throws InvalidAmountException{
        Customer customer = getCustomer(accountId);
        if(amount <= 0){
           throw new InvalidAmountException();
        }
        double interest = checkInterest(customer.getAccount().getBalance(), amount);
        double amountToDeposit = amount + (amount + interest);
        database.updateAccount(accountId, customer.getAccount().getBalance() + amountToDeposit);                
    }

    public double checkInterest(double balance, double amount) {
       double interest = 0;
       interest = (balance + amount > 10000)? 0.05 : 0.02;
       return interest;
    }

    public double getTransactionFee(AccountType accountType) {
       double transactionFee = 0;
       switch(accountType){
           case Checking:
           case Savings:
               transactionFee = 5;
               break;
           case Undefined:
           default:
               transactionFee = 0;
               break;
       }
        return transactionFee;
    }
}
