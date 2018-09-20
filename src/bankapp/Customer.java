/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankapp;

import java.io.Serializable;
import java.util.function.Predicate;

/**
 *
 * @author marshall
 */
public class Customer implements Serializable{

    private final String firstName;
    private final String lastName;
    private final String ssn;
    private final Account account;
    
   /* public Predicate<String> checkIfStringIsEmpty = input -> input.length()!= 0;
    public Predicate<String> checkIfStringContainMinus = input -> !input.contains("-"); */
    
    Customer(String firstName, String lastName, String ssn, Account account){
        this.firstName = firstName;
        this.lastName = lastName;
        this.ssn = ssn;
        this.account = account;
    }
    
    @Override
    public String toString(){
        return "\nCustomer Information\n" +
                "First Name: " + getFirstName() +
                "Last Name: " + getLastName() +
                "SSN: " + getSsn() +
                account;                
    }
    
    Account getAccount(){
        return account;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @return the ssn
     */
    public String getSsn() {
        return ssn;
    }
}
