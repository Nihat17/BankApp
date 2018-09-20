/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankapp;

import java.io.Serializable;
import javax.swing.JOptionPane;

public abstract class Account implements Serializable{
    private double balance = 0;    
    private int accountNumber;    
    
    public Account(int accountId){
     accountNumber = accountId;
    }
    
    public abstract AccountType getAccountType();            
    
    public double getBalance() {
        return balance;
    }
    
    public void setBalance(double balance) {
        this.balance = balance;
    }
           
    public int getAccountNumber() {
        return accountNumber;
    }
           
    void setAccountNumber(int accountNumber){
        this.accountNumber = accountNumber;
    }  
  
}
